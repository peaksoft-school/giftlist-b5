package kg.giftlist.giftlist.apis;

import com.google.firebase.auth.FirebaseAuthException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user.UserResponse;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Authentication API", description = "Any users can authenticate")
public class AuthApi {
    private final UserServiceImpl service;

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
    @PostMapping("auth/google")
    public AuthResponse loginWithGoogle(@RequestParam String token) throws FirebaseAuthException {
        return service.authenticateWithGoogle(token);
    }

}