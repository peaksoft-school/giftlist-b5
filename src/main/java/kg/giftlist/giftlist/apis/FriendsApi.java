package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.user.SimpleResponse;
import kg.giftlist.giftlist.dto.user_friends.CommonUserProfileResponse;
import kg.giftlist.giftlist.dto.user_friends.UserFriendProfileResponse;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/friends")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Friends API", description = "Users with role  \"User\" can add, delete friends")
public class FriendsApi {

    private final UserServiceImpl userService;

    @Operation(summary = "Request to friend", description = "User can send request to user")
    @PostMapping("/request/{userId}")
    public SimpleResponse requestToFriend(@PathVariable Long userId) {
        return userService.requestToFriend(userId);
    }

    @Operation(summary = "Accept to friend", description = "User can accept to user")
    @PostMapping("/accept/{userId}")
    public SimpleResponse acceptToFriend(@PathVariable Long userId) {
        return userService.acceptToFriend(userId);
    }

    @Operation(summary = "Reject friend", description = "User can reject friend")
    @PostMapping("/reject/{userId}")
    public SimpleResponse rejectFriend(@PathVariable Long userId) {
        return userService.rejectFriend(userId);
    }

    @Operation(summary = "Delete friend", description = "User can delete friend")
    @PostMapping("/delete/{userId}")
    public SimpleResponse deleteFriend(@PathVariable Long userId) {
        return userService.deleteFriend(userId);
    }

    @Operation(summary = "Get Friend Profile", description = "User can get friend profile")
    @GetMapping("profile/{userId}")
    public UserFriendProfileResponse getFriendProfile(@PathVariable Long userId) {
        return userService.getFriendProfile(userId);
    }

    @Operation(summary = "Get All Friends", description = "User can get all friends list")
    @GetMapping
    public List<UserFriendProfileResponse> getAllFriends() {
        return userService.getAllFriends();
    }

    @Operation(summary = "Get All Request to Friends", description = "User can get all request to friends list")
    @GetMapping("/requests")
    public List<UserFriendProfileResponse> getAllRequestToFriends() {
        return userService.getAllRequestToFriends();
    }

    @Operation(summary = "Get common user profile", description = "User can get common user profile")
    @GetMapping("/common_profile/{userId}")
    public CommonUserProfileResponse getCommonUserProfile(@PathVariable Long userId) {
        return userService.getCommonFriendProfile(userId);
    }

}
