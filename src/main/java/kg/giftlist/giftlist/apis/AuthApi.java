package kg.giftlist.giftlist.apis;

import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user.UserResponse;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Authentication API", description = "Users with role \"Admin\", \"User\" can authenticate")
public class AuthApi {
    private final UserServiceImpl service;

    @Operation(summary = "Registration ", description = "Any user can register")
    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Sign in ", description = "Any user can login")
    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return service.authenticate(authRequest);
    }
}
