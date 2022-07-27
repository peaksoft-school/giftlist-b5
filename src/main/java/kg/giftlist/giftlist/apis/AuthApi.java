package kg.giftlist.giftlist.apis;

import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user.UserResponse;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("domain/api/public")
@RequiredArgsConstructor
public class AuthApi {
    private final UserServiceImpl service;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public UserResponse register(@RequestBody UserRequest request) {
        return service.create(request);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return service.authenticate(authRequest);
    }
}
