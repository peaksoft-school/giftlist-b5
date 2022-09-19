package kg.giftlist.giftlist.db.service.impl;

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
import kg.giftlist.giftlist.dto.user_friends.CommonUserProfileResponse;
import kg.giftlist.giftlist.dto.user_friends.UserFriendProfileResponse;
import kg.giftlist.giftlist.enums.Role;
import kg.giftlist.giftlist.exception.AlreadyExistException;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.config.security.JwtUtils;
import kg.giftlist.giftlist.db.service.UserService;
import kg.giftlist.giftlist.exception.UserForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import java.util.List;
import java.util.ArrayList;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepo;
    private final JwtUtils jwtUtils;
    private final UserEditMapper editMapper;
    private final UserViewMapper viewMapper;
    private final PasswordEncoder encoder;

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        User user = userRepo.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "user with email = " + authRequest.getEmail() + " not found!"
                ));

        if (!encoder.matches(authRequest.getPassword(), user.getPassword())) {
            log.error("invalid password");
            throw new BadCredentialsException(
                    "invalid password"
            );
        }
        if (user.getIsBlock()==true){
            return null;
        }
        String jwt = jwtUtils.generateJwt(user);
        log.info("User successfully logged in");
        return new AuthResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoto(),
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
            log.info("Successfully saved user with id: {} in db", newUser.getId());
        }
        else {
            user = userRepo.findByEmail(decodedToken.getEmail()).orElseThrow(()->
                    new NotFoundException("Not found email "));
            log.error("Not found email");
        }
        return new AuthResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoto(),
                user.getEmail(),
                jwtUtils.generateJwt(user),
                user.getRole()
        );
    }

    public UserResponse userRegister(UserRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            log.error("Email already registered!");
            throw new AlreadyExistException(
                    "Email already registered!"
            );
        }
        User user = editMapper.create(request);
        user.setPassword(encoder.encode(request.getPassword()));
        userRepo.save(user);
        log.info("Successfully saved user with id: {} in db", user.getId());
        return viewMapper.viewUser(user);
    }

    @Override
    public UserProfileResponse findById() {
        User user1 = getAuthenticatedUser();
            return viewMapper.viewUserProfile(user1);
    }

    @Override
    @Transactional
    public AuthResponse changeUserPassword(UserChangePasswordRequest userChangePasswordRequest) {
        User user = getAuthenticatedUser();
        if (!encoder.matches(userChangePasswordRequest.getCurrentPassword(), user.getPassword())) {
            log.error("invalid password");
            throw new NotFoundException(
                    "invalid password");
        }else {
            editMapper.changePassword(user, userChangePasswordRequest);
            user.setPassword(encoder.encode(userChangePasswordRequest.getNewPassword()));
            log.info("Password successfully changed");
            return new AuthResponse(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoto(),
                    user.getEmail(),
                    jwtUtils.generateJwt(user),
                    user.getRole()
            );
        }
    }

    @Override
    public List<UserResponse> findUser(String name) {
        return view(userRepo.searchAllByFirstNameAndLastName(name.toUpperCase()));
    }

    public List<UserResponse> findUnblockUserByName(String name) {
        return view(userRepo.searchUnblockUsersByFirstNameAndLastName(name.toUpperCase()));
    }

    @Override
    @Transactional
    public AuthResponse changeNewPassword(Long userId, String newPassword) {
        User user = userRepo.findById( userId ).orElseThrow(() -> new UsernameNotFoundException(
                "user with id = " + userId + " not found!"
        ));
        user.setPassword( encoder.encode( newPassword ) );

        return new AuthResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoto(),
                user.getEmail(),
                jwtUtils.generateJwt(user),
                user.getRole()
        );

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

    @Override
    @Transactional
    public SimpleResponse requestToFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (friend.equals(user)) {
            throw new UserForbiddenException("You can not request to you");
        }
        if (friend.getRequestToFriends().contains(user)) {
            log.error("Request already sent");
            throw new AlreadyExistException("Request already sent");
        }
        friend.addRequestToFriend(user);
        log.info("Request to friend successfully send");
        return new SimpleResponse("Success","Request to friend successfully send");
    }

    @Override
    @Transactional
    public SimpleResponse cancelRequestToFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (friend.getRequestToFriends().contains(user)) {
            friend.getRequestToFriends().remove(user);
        }else {
            throw new NotFoundException("No request to friend");
        }
        return new SimpleResponse("Success","Request to friend successfully cancel");
    }

    @Override
    @Transactional
    public SimpleResponse acceptToFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (user.getRequestToFriends().contains(friend)) {
            friend.acceptToFriend(user);
            user.getRequestToFriends().remove(friend);
            user.acceptToFriend(friend);
            log.info("Successfully accept to friend with id: {}", friend.getId());
        }else {
            log.error("You are already friend");
            throw new AlreadyExistException("You are already friend");
        }
        return new SimpleResponse("Accepted","Successfully accept to friend");
    }

    @Override
    @Transactional
    public SimpleResponse rejectFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (user.getRequestToFriends().contains(friend)) {
            user.getRequestToFriends().remove(friend);
            log.info("Successfully rejected user with id {}", friend.getId());
        }else {
            log.error("You have not request to reject");
            throw new NotFoundException("You have not request to reject");
        }
        return new SimpleResponse("Rejected","Successfully rejected");
    }

    @Override
    @Transactional
    public SimpleResponse deleteFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (user.getFriends().contains(friend)) {
            friend.getFriends().remove(user);
            user.getFriends().remove(friend);
            log.info("Successfully deleted user with id: {}", friend.getId());
        }else {
            log.error("You have not friend with id "+friend.getId());
            throw new NotFoundException("You have not friend with id "+friend.getId());
        }
        return new SimpleResponse("Deleted","Successfully deleted");
    }

    @Override
    public UserFriendProfileResponse getFriendProfile(Long friendId) {
        if (friendId != null) {
            User friend = findByUserId(friendId);
            return viewMapper.viewFriendProfile(friend);
        } else {
            throw new NotFoundException(
                    String.format("not found=%s id", friendId)
            );
        }
    }

    @Override
    public List<UserFriendProfileResponse> getAllFriends() {
        User user = getAuthenticatedUser();
        return viewMapper.getAllFriends(userRepo.findAllFriends(user.getId()));
    }

    @Override
    public List<UserFriendProfileResponse> getAllRequestToFriends() {
        User user = getAuthenticatedUser();
        return viewMapper.getAllFriends(userRepo.findAllRequestToFriends(user.getId()));
    }

    @Override
    public CommonUserProfileResponse getCommonFriendProfile(Long userId) {

        User user = userRepo.findById(userId).orElseThrow(() ->
                new NotFoundException("User with id "+userId+" not found"));
        return viewMapper.viewCommonFriendProfile(user);
    }
}