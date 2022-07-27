package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.mapper.UserEditMapper;
import kg.giftlist.giftlist.dto.mapper.UserViewMapper;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user.UserResponse;
import kg.giftlist.giftlist.exception.IsEmptyException;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.security.JwtUtils;
import kg.giftlist.giftlist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final JwtUtils jwtUtils;
    private final UserEditMapper editMapper;
    private final UserViewMapper viewMapper;
    private final PasswordEncoder encoder;

    @Override
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
//        encoder.matches(authRequest.getPassword(), user.getPassword());
        String jwt = jwtUtils.generateJwt(user);

        return new AuthResponse(user.getId(),
                user.getEmail(),
                jwt,
                user.getRole()
        );

    }

    @Override
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

}
