package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.user.AdminUserGetAllResponse;
import kg.giftlist.giftlist.dto.user_info.UserInfoRequest;
import kg.giftlist.giftlist.dto.user_info.UserInfoResponse;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.UserInfo;
import kg.giftlist.giftlist.services.AdminService;
import kg.giftlist.giftlist.services.UserInfoService;
import kg.giftlist.giftlist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
@CrossOrigin
@Tag(name = "Admin", description = "Admin accessible apis")
public class AdminApi {
    private final AdminService adminService;

    @Operation(summary = "Get all users", description = "Get all users ")
    @GetMapping("/users")
    public List<AdminUserGetAllResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/users/{id}")
    public UserInfoResponse getUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }

    @Operation(summary = "Update user",description = "update a user")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserInfoRequest request){
        return adminService.updateByIdInUser(id,request);
    }
    @Operation(summary = "Delete user ",description = "delete user!")
    @DeleteMapping({"/users/{id}"})
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return adminService.deleteUserById(id);
    }
}
