package kg.giftlist.giftlist.api;

import com.google.firebase.auth.FirebaseAuthException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.impl.MailingListServiceImpl;
import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.user.SimpleResponse;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user.UserResponse;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/public")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Authentication API", description = "Any users can authenticate")
public class AuthApi {

    private final UserServiceImpl service;
    private final UserRepository userRepository;
    private final MailingListServiceImpl mailingListService;

    @Operation(summary = "Registration ", description = "Any user can do registration")
    @PostMapping("register")
    public UserResponse register(@RequestBody UserRequest request) {
        return service.userRegister(request);
    }

    @Operation(summary = "Login", description = "Only registered users can login")
    @PostMapping("login")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return service.authenticate(authRequest);
    }

    @Operation(summary = "Google authentication", description = "Any users with Google account can authenticate")
    @PostMapping("auth-google")
    public AuthResponse loginWithGoogle(@RequestParam String token) throws FirebaseAuthException {
        return service.authenticateWithGoogle(token);
    }

    @Operation(summary = "Change password", description = "User can change password")
    @PostMapping("password/{userId}")
    public AuthResponse newPassword(@PathVariable Long userId, @RequestParam String newPassword) {
        return service.changeNewPassword(userId, newPassword);
    }

    @Operation(summary = "Send link to user email", description = "Send change password link to user email")
    @PostMapping("password")
    public SimpleResponse forgotPassword(@RequestParam String to, @RequestParam String link) throws Exception {
        if (!userRepository.existsByEmail(to)) {
            return new SimpleResponse("WRONG", "This email is not in the database!");
        }
        mailingListService.sentLink(to, link);
        return new SimpleResponse("OK", "Successfully sent to your email");
    }

}