package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.services.impl.UserInfoServiceImpl;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/profile")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "User API", description = "Users with role  \"User\" can create, update profile")
public class UserApi {

    private final UserInfoServiceImpl userInfoService;
    private final UserServiceImpl userService;

    @Operation(summary = "Create ", description = "Create user profile")
    @PostMapping("/create")
    public UserInfoResponse saveUserProfile(@RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.create(userInfoRequest);
    }

    @Operation(summary = "update ", description = "User can update profile")
    @PostMapping("/edit/{userInfoId}")
    public UserInfoResponse updateUserProfile(@PathVariable Long userInfoId, @RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.update(userInfoId, userInfoRequest);
    }

    @Operation(summary = "Get user profile ", description = "Find by id user profile")
    @GetMapping("/{userId}")
    public UserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @Operation(summary = "Change password ", description = "User can change password")
    @PostMapping("/{userId}")
    public SimpleResponse changePassword(@PathVariable Long userId, @RequestBody UserChangePasswordRequest request) {
        return userService.changeUserPassword(userId, request);
    }

}
