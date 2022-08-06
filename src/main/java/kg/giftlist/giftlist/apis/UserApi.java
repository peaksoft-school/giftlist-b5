package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.services.impl.UserInfoServiceImpl;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "User API", description = "Users with role  \"User\" can authenticate")
public class UserApi {

    private final UserInfoServiceImpl userInfoService;
    private final UserServiceImpl userService;

    @PostMapping("/profile/create")
    public UserInfoResponse saveUserProfile(@RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.create(userInfoRequest);
    }

    @PostMapping("/profile/edit/{userInfoId}")
    public UserInfoResponse updateUserProfile(@PathVariable Long userInfoId, @RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.update(userInfoId,userInfoRequest);
    }

    @GetMapping("/empty_profile/{userId}")
    public UserFirstProfileResponse getUserEmptyProfile(@PathVariable Long userId) {
        return userService.getUserFirstProfile(userId);
    }

    @GetMapping("/profile/{userId}")
    public UserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/password/{userId}")
    public UserPasswordChangedResponse changePassword(@PathVariable Long userId, @RequestBody UserChangePasswordRequest request) {
        return userService.changeUserPassword(userId, request);
    }





}
