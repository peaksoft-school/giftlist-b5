package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.AuthResponse;
import kg.giftlist.giftlist.db.service.impl.UserInfoServiceImpl;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import kg.giftlist.giftlist.dto.user.UserChangePasswordRequest;
import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.dto.user.UserProfileResponse;
import kg.giftlist.giftlist.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/profile")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "User API", description = "Users with role \"User\" can create, update profile")
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
    @PostMapping("/password")
    public AuthResponse changePassword(@RequestBody UserChangePasswordRequest request) {
        return userService.changeUserPassword(request);
    }

    @Operation(summary = "Search users", description = "User can search by first name and last name")
    @GetMapping("/{name}")
    public List<UserResponse> findUser(@PathVariable String name) {
        return userService.findUnblockUserByName(name);
    }

}
