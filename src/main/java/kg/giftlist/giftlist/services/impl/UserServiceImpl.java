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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import javax.ws.rs.ForbiddenException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepo;
    private final JwtUtils jwtUtils;
    private final UserEditMapper editMapper;
    private final UserViewMapper viewMapper;
    private final PasswordEncoder encoder;


    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
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
        logger.info("User successfully logged in");
        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                jwt,
                user.getRole()
        );
    }

    public AuthResponse authenticateWithGoogle(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

        User user;
        if (!userRepo.existsByEmail(decodedToken.getEmail())) {
            User newUser = new User(
                    decodedToken.getName(),
                    decodedToken.getEmail(),
                    encoder.encode(decodedToken.getEmail()),
                    Role.USER
            );
            user = userRepo.save(newUser);
        }
        else {
            user = userRepo.findByEmail(decodedToken.getEmail()).get();
        }
        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                jwtUtils.generateJwt(user),
                user.getRole()
        );
    }

    public UserResponse userRegister(UserRequest request) {
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

    @Override
    public List<UserResponse> findUser(String name) {
        return view(userRepo.searchAllByFirstNameAndLastName(name.toUpperCase()));
    }

    private List<UserResponse> view(List<User> users){
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(new UserResponse(user));
        }
        return responses;
    }

    public User findByUserId(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                String.format("user with id = %s does not exists", userId)
                ));
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepo.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }

}