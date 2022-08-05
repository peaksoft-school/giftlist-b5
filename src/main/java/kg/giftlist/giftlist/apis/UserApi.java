package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.services.impl.UserInfoServiceImpl;
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

    @PostMapping("/profile/create")
    public UserInfoResponse saveUserProfile(@RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.create(userInfoRequest);
    }

    @PostMapping("/profile/edit/{userInfoId}")
    public UserInfoResponse updateUserProfile(@PathVariable Long userInfoId, @RequestBody UserInfoRequest userInfoRequest) {
        return userInfoService.update(userInfoId,userInfoRequest);
    }

    @GetMapping("/{userInfoId}")
    public UserInfoResponse findById(@PathVariable Long userInfoId) {
        return
    }




}
