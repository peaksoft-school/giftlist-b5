package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import kg.giftlist.giftlist.dto.user.UserProfileResponse;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.services.AdminService;
import kg.giftlist.giftlist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/users")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Admin", description = "Admin accessible apis")
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
    public UserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @Operation(summary = "block user", description = "block user by id")
    @PutMapping("/block/{userId}")
    public ResponseEntity<?> block(@PathVariable("userId") Long id) {
        return adminService.blockUser(id);
    }

    @Operation(summary = "UnBlock user", description = "UnBlock user by id")
    @PutMapping("/unBlock/{userId}")
    public ResponseEntity<?> unBlock(@PathVariable("userId") Long id) {
        return adminService.unBlockUser(id);
    }
}
