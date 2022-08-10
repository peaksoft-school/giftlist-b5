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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
@CrossOrigin
@Tag(name = "Admin", description = "Admin accessible apis")
public class AdminApi {

    private final AdminService adminService;
    private final UserService userService;

    @Operation(summary = "Get all users", description = "Get all users ")
    @GetMapping("/users")
    public List<AdminPageUserGetAllResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Operation(summary = "Get user profile ", description = "Find by id user profile")
    @GetMapping("/{userId}")
    public UserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @Operation(summary = "block user", description = "block user by id")
    @PutMapping("/block/{id}")
    public ResponseEntity<?> block(@PathVariable Long id) {
        return adminService.blockUser(id);
    }

    @Operation(summary = "UnBlock user", description = "UnBlock user by id")
    @PutMapping("/un_block/{id}")
    public ResponseEntity<?> unBlock(@PathVariable Long id) {
        return adminService.unBlockUser(id);
    }

    @Operation(summary = "block gift", description = "block gift by id")
    @PutMapping("/block/gift/{id}")
    public ResponseEntity<?> blockGift(@PathVariable Long id) {
        return adminService.blockGift(id);
    }

    @Operation(summary = "block holiday", description = "block holiday by id")
    @PutMapping("/block/holiday/{id}")
    public ResponseEntity<?> blockHoliday(@PathVariable Long id) {
        return adminService.blockHoliday(id);
    }

    @Operation(summary = "block wish", description = "block wish by id")
    @PutMapping("/block/wish/{id}")
    public ResponseEntity<?> blockWish(@PathVariable Long id) {
        return adminService.blockWish(id);
    }
}
