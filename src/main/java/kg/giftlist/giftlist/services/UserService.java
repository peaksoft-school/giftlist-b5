package kg.giftlist.giftlist.services;
import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.dto.user_friends.UserFriendProfileResponse;

import java.util.List;

public interface UserService {

    UserProfileResponse findById(Long userId);

    SimpleResponse changeUserPassword(Long userId, UserChangePasswordRequest userChangePasswordRequest);

    SimpleResponse requestToFriend(Long friendId);

    SimpleResponse acceptToFriend(Long friendId);

    SimpleResponse rejectFriend(Long friendId);

    SimpleResponse deleteFriend(Long friendId);

    UserFriendProfileResponse getFriendProfile(Long friendId);

    List<UserFriendProfileResponse> getAllFriend();



}
