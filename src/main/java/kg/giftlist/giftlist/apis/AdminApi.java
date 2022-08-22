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

    @Operation(summary = "block user", description = "block user by id")
    @PutMapping("/block/{userId}")
    public SimpleResponse block(@PathVariable("userId") Long id) {
        return adminService.blockUser(id);
    }

    @Operation(summary = "UnBlock user", description = "UnBlock user by id")
    @PutMapping("/unBlock/{userId}")
    public SimpleResponse unBlock(@PathVariable("userId") Long id) {
        return adminService.unBlockUser(id);
    }
}
