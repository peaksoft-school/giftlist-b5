package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user.UserResponse;


public interface UserService {

     AuthResponse authenticate(AuthRequest authRequest);

//     UserResponse create(UserRequest request);

}
