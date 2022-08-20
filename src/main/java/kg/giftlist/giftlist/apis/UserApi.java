package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.db.service.impl.UserInfoServiceImpl;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/profile")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "User API", description = "Users with role  \"User\" can create, update profile")
public class UserApi {

    private final UserInfoServiceImpl userInfoService;
    private final UserServiceImpl userService;

    @Operation(summary = "Add user profile information", description = "User can add profile information")
    @PostMapping("/create")
    public UserInfoResponse saveUserProfile(@RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.create(userInfoRequest);
    }

    @Operation(summary = "Update user profile information ", description = "User can update profile information")
    @PostMapping("/edit/{userInfoId}")
    public UserInfoResponse updateUserProfile(@PathVariable Long userInfoId, @RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.update(userInfoId, userInfoRequest);
    }

    @Operation(summary = "Get user profile ", description = "Find by id user profile")
    @GetMapping("/me")
    public UserProfileResponse getUserProfile() {
        return userService.findById();
    }

    @Operation(summary = "Change password ", description = "User can change password")
    @PostMapping("/{userId}")
    public SimpleResponse changePassword(@PathVariable Long userId, @RequestBody UserChangePasswordRequest request) {
        return userService.changeUserPassword(userId, request);
    }

    @Operation(summary = "Search users",description = "Find user by name and surname")
    @GetMapping("/search/{name}")
    public List<UserResponse> findUser(@PathVariable String name){
        return userService.findUser(name);
    }

}
