package kg.giftlist.giftlist.services.impl;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.mapper.UserEditMapper;
import kg.giftlist.giftlist.dto.mapper.UserViewMapper;
import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.enums.Role;
import kg.giftlist.giftlist.exception.IsEmptyException;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.security.JwtUtils;
import kg.giftlist.giftlist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepo;
    private final JwtUtils jwtUtils;
    private final UserEditMapper editMapper;
    private final UserViewMapper viewMapper;
    private final PasswordEncoder encoder;

    @PostConstruct
    void init() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/firebase/giftlist-firebase.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        User user = userRepo.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "user with email = " + authRequest.getEmail() + " not found!"
                ));

        if (!encoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(
                    "invalid password"
            );
        }
        String jwt = jwtUtils.generateJwt(user);

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                jwt,
                user.getRole()
        );
    }

    public AuthResponse authenticateWithGoogle(String token) throws FirebaseAuthException {
        FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token);

        User user = null;
        if (!userRepo.existsByEmail(firebaseToken.getEmail())) {
            User newUser = new User(
                    firebaseToken.getName(),
                    firebaseToken.getEmail(),
                    encoder.encode(firebaseToken.getEmail()),
                    Role.USER
            );
            user = userRepo.save(newUser);
        }
        else {
            user = userRepo.findByEmail(firebaseToken.getEmail()).get();
        }
        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                jwtUtils.generateJwt(user),
                user.getRole()
        );
    }

    public UserResponse create(UserRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new IsEmptyException(
                    "this email is already have in!"
            );
        }
        User user = editMapper.create(request);
        user.setPassword(encoder.encode(request.getPassword()));
        userRepo.save(user);
        return viewMapper.viewUser(user);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepo.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("Username not found "));
    }

    @Override
    public UserProfileResponse findById(Long userId) {
        if (userId != null) {
            User user = findByUserId(userId);
            return viewMapper.viewUserProfile(user);
        } else {
            throw new NotFoundException(
                    String.format("not found=%s id", userId)
            );
        }
    }

    @Override
    @Transactional
    public SimpleResponse changeUserPassword(Long userId, UserChangePasswordRequest userChangePasswordRequest) {
        User user = findByUserId(userId);
        if (!encoder.matches(userChangePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new NotFoundException(
                    "invalid password");
        }else {
            editMapper.changePassword(user, userChangePasswordRequest);
            user.setPassword(encoder.encode(userChangePasswordRequest.getNewPassword()));
            userRepo.save(user);
            return new SimpleResponse("Changed","Password successfully changed");
        }
    }

    public User findByUserId(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                String.format("user with id = %s does not exists", userId)
                ));
    }
}