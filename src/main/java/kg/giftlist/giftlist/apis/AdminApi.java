package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import kg.giftlist.giftlist.db.service.AdminService;
import kg.giftlist.giftlist.db.service.UserService;
import kg.giftlist.giftlist.dto.user_friends.CommonUserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/users")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Admin API", description = "User with role 'Admin'  can block, unblock and get users")
public class AdminApi {

    private final AdminService adminService;
    private final UserService userService;

    @Operation(summary = "Get all users", description = "Get all users ")
    @GetMapping
    public List<AdminPageUserGetAllResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Operation(summary = "Get user profile ", description = "Find by id user profile")
    @GetMapping("/{userId}")
    public CommonUserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userService.getCommonFriendProfile(userId);
    }

    @Operation(summary = "Block User", description = "block user by id")
    @PutMapping("/blockUser/{userId}")
    public SimpleResponse block(@PathVariable("userId") Long id) {
        return adminService.blockUser(id);
    }

    @Operation(summary = "UnBlock User", description = "UnBlock user by id")
    @PutMapping("/unBlockUser/{userId}")
    public SimpleResponse unBlock(@PathVariable("userId") Long id) {
        return adminService.unBlockUser(id);
    }

    @Operation(summary = "Block Wish", description = "block wish by id")
    @PutMapping("/blockWish/{wishId}")
    public SimpleResponse blockWish(@PathVariable Long wishId) {
        return adminService.blockWish(wishId);
    }

    @Operation(summary = "UnBlock Wish", description = "UnBlock wish by id")
    @PutMapping("/unBlockWish/{wishId}")
    public SimpleResponse unBlockWish(@PathVariable Long wishId) {
        return adminService.unBlockWish(wishId);
    }


    @Operation(summary = "Block Holiday", description = "block holiday by id")
    @PutMapping("/blockHolidday/{holidayId}")
    public SimpleResponse blockHoliday(@PathVariable Long holidayId) {
        return adminService.blockHoliday(holidayId);
    }

    @Operation(summary = "UnBlock Holiday", description = "UnBlock holiday by id")
    @PutMapping("/unBlockHoliday/{holidayId}")
    public SimpleResponse unBlockHoliday(@PathVariable Long holidayId) {
        return adminService.unBlockHoliday(holidayId);
    }

    @Operation(summary = "Block Gift", description = "block gift by id")
    @PutMapping("/blockGift/{giftId}")
    public SimpleResponse blockGift(@PathVariable Long giftId) {
        return adminService.blockGift(giftId);
    }

    @Operation(summary = "UnBlock Gift", description = "UnBlock gift by id")
    @PutMapping("/unBlockGift/{giftId}")
    public SimpleResponse unBlockGift(@PathVariable Long giftId) {
        return adminService.unBlockGift(giftId);
    }


}
