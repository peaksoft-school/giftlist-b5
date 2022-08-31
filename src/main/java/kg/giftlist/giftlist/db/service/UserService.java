package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.dto.user_friends.CommonUserProfileResponse;
import kg.giftlist.giftlist.dto.user_friends.UserFriendProfileResponse;

import java.util.List;

public interface UserService {

    UserProfileResponse findById();

    SimpleResponse changeUserPassword(Long userId, UserChangePasswordRequest userChangePasswordRequest);

    SimpleResponse requestToFriend(Long friendId);

    SimpleResponse cancelRequestToFriend(Long friendId);

    SimpleResponse acceptToFriend(Long friendId);

    SimpleResponse rejectFriend(Long friendId);

    SimpleResponse deleteFriend(Long friendId);

    UserFriendProfileResponse getFriendProfile(Long friendId);

    List<UserFriendProfileResponse> getAllFriends();

    List<UserFriendProfileResponse> getAllRequestToFriends();

    CommonUserProfileResponse getCommonFriendProfile(Long userId);

    List<UserResponse> findUser(String name);

}
