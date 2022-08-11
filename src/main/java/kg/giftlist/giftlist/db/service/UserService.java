package kg.giftlist.giftlist.db.service;
import kg.giftlist.giftlist.dto.user.*;

import java.util.List;

public interface UserService {

    UserProfileResponse findById(Long userId);

    SimpleResponse changeUserPassword(Long userId, UserChangePasswordRequest userChangePasswordRequest);

    List<UserResponse> findUser(String name);

}
