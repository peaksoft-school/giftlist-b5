package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.GiftServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import kg.giftlist.giftlist.db.service.AdminService;
import kg.giftlist.giftlist.db.service.UserService;
import kg.giftlist.giftlist.dto.user.UserResponse;
import kg.giftlist.giftlist.dto.user_friends.CommonUserProfileResponse;
import kg.giftlist.giftlist.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Admin API", description = "User with role 'Admin' can block, unblock and get users")
public class AdminApi {

    private final AdminService adminService;
    private final UserService userService;
    private final GiftServiceImpl giftService;

    @Operation(summary = "Get all users", description = "Get all users ")
    @GetMapping("users")
    public List<AdminPageUserGetAllResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Operation(summary = "Get user profile ", description = "Find by id user profile")
    @GetMapping("user/{userId}")
    public CommonUserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userService.getCommonFriendProfile(userId);
    }

    @Operation(summary = "Block User", description = "Block user by id")
    @PutMapping("block-user/{userId}")
    public SimpleResponse block(@PathVariable("userId") Long id) {
        return adminService.blockUser(id);
    }

    @Operation(summary = "Unblock user", description = "Unblock user by id")
    @PutMapping("unblock-user/{userId}")
    public SimpleResponse unBlock(@PathVariable("userId") Long id) {
        return adminService.unBlockUser(id);
    }

    @Operation(summary = "Block wish", description = "Block wish by id")
    @PutMapping("block-wish/{wishId}")
    public SimpleResponse blockWish(@PathVariable Long wishId) {
        return adminService.blockWish(wishId);
    }

    @Operation(summary = "Unblock wish", description = "Unblock wish by id")
    @PutMapping("unblock-wish/{wishId}")
    public SimpleResponse unBlockWish(@PathVariable Long wishId) {
        return adminService.unBlockWish(wishId);
    }

    @Operation(summary = "Block gift", description = "Block gift by id")
    @PutMapping("block-gift/{giftId}")
    public SimpleResponse blockGift(@PathVariable Long giftId) {
        return adminService.blockGift(giftId);
    }

    @Operation(summary = "Unblock gift", description = "Unblock gift by id")
    @PutMapping("unblock-gift/{giftId}")
    public SimpleResponse unBlockGift(@PathVariable Long giftId) {
        return adminService.unBlockGift(giftId);
    }

    @Operation(summary = "Search users", description = "Admin can search by first name and last name")
    @GetMapping("search/{name}")
    public List<UserResponse> findUser(@PathVariable String name) {
        return userService.findUser(name);
    }

    @Operation(summary = "Get all gifts", description = "Admin can get all gifts")
    @GetMapping("gifts")
    public List<GiftResponse> getAllGifts() {
        return giftService.getAllGiftsForAdmin();
    }

    @Operation(summary = "Search gifts by filter", description = "Admin can search gifts by filter")
    @GetMapping("filter")
    public List<GiftResponse> filter(
            @RequestParam(required = false, defaultValue = "all") String search,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long subCategoryId) {
        return giftService.filterGiftForAdmin(search, status, categoryId, subCategoryId);
    }

    @Operation(summary = "Find gift by id", description = "Admin can find gift by id")
    @GetMapping("gift/{giftId}")
    public GiftResponse findGiftById(@PathVariable Long giftId) {
        return giftService.getGiftById(giftId);
    }

}
