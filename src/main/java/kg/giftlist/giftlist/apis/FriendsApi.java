package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.user.SimpleResponse;
import kg.giftlist.giftlist.dto.user_friends.CommonUserProfileResponse;
import kg.giftlist.giftlist.dto.user_friends.UserFriendProfileResponse;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/users/friends")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Friends API", description = "Users with role  \"User\" can add, delete friends")
public class FriendsApi {

    private final UserServiceImpl userService;

    @Operation(summary = "Request to friend", description = "User can send request to friend")
    @PostMapping("request/{userId}")
    public SimpleResponse requestToFriend(@PathVariable Long userId) {
        return userService.requestToFriend(userId);
    }

    @Operation(summary = "Cancel Request to friend", description = "User can cancel request to friend")
    @PostMapping("cancel/{userId}")
    public SimpleResponse cancelRequestToFriend(@PathVariable Long userId) {
        return userService.cancelRequestToFriend(userId);
    }

    @Operation(summary = "Accept request to friend", description = "User can accept request to friend")
    @PostMapping("accept/{userId}")
    public SimpleResponse acceptToFriend(@PathVariable Long userId) {
        return userService.acceptToFriend(userId);
    }

    @Operation(summary = "Reject request to friend", description = "User can reject request to friend ")
    @PostMapping("reject/{userId}")
    public SimpleResponse rejectFriend(@PathVariable Long userId) {
        return userService.rejectFriend(userId);
    }

    @Operation(summary = "Delete friend", description = "User can delete friend")
    @PostMapping("/{id}")
    public SimpleResponse deleteFriend(@PathVariable Long id) {
        return userService.deleteFriend(id);
    }

    @Operation(summary = "Get Friend Profile", description = "User can get friend profile")
    @GetMapping("/{userId}")
    public UserFriendProfileResponse getFriendProfile(@PathVariable Long userId) {
        return userService.getFriendProfile(userId);
    }

    @Operation(summary = "Get All Friends", description = "User can get all friends list")
    @GetMapping
    public List<UserFriendProfileResponse> getAllFriends() {
        return userService.getAllFriends();
    }

    @Operation(summary = "Get All Request to Friends", description = "User can get all request to friends list")
    @GetMapping("requests")
    public List<UserFriendProfileResponse> getAllRequestToFriends() {
        return userService.getAllRequestToFriends();
    }

    @Operation(summary = "Get common user profile", description = "User can get common user profile")
    @GetMapping("profile/{userId}")
    public CommonUserProfileResponse getCommonUserProfile(@PathVariable Long userId) {
        return userService.getCommonFriendProfile(userId);
    }

}
