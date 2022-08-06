package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.AuthRequest;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.dto.user.*;


public interface UserService {

    UserFirstProfileResponse getUserFirstProfile(Long userId);

    UserProfileResponse findById(Long userId);

    UserPasswordChangedResponse changeUserPassword(Long userId, UserChangePasswordRequest userChangePasswordRequest);
}
