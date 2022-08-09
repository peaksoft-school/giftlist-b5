package kg.giftlist.giftlist.services;
import kg.giftlist.giftlist.dto.user.*;

public interface UserService {

    UserProfileResponse findById(Long userId);

    SimpleResponse changeUserPassword(Long userId, UserChangePasswordRequest userChangePasswordRequest);

}
