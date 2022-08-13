package kg.giftlist.giftlist.db.service;
import kg.giftlist.giftlist.dto.user.*;

public interface UserService {

    UserProfileResponse findById(Long userId);

    SimpleResponse changeUserPassword(Long userId, UserChangePasswordRequest userChangePasswordRequest);

}
