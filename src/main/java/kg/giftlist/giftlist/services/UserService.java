package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user.UserResponse;
import org.springframework.stereotype.Service;


public interface UserService {

    public AuthResponse authenticate(AuthRequest authRequest);

    public UserResponse create(UserRequest request);

}
