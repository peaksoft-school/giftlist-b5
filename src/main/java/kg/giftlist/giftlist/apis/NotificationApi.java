package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.NotificationServiceImpl;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notifications")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Notification API", description = "User with role \"User\"  can findAll and findById notifications")
public class NotificationApi {

    private final NotificationServiceImpl notificationService;
    private final UserServiceImpl userService;

    @Operation(summary = "Get user", description = "User can get user by id")
    @GetMapping("/user/{id}")
    public UserInfoResponse findUserById(@PathVariable Long id) {
        return notificationService.findUserByUserId(id);
    }

    @Operation(summary = "Get user", description = "User can get user by username")
    @GetMapping("/user/{username}")
    public UserInfoResponse findUserByUsername(@PathVariable String username) {
        return notificationService.findUserByUsername(username);
    }

    @Operation(summary = "Find gift by id", description = "The user can find gift")
    @GetMapping("gift/{giftId}")
    public GiftResponse findGiftById(@PathVariable Long giftId) {
        return notificationService.findGiftById(giftId);
    }

    @Operation(summary = "Find wish by id", description = "The user can find wish")
    @GetMapping("wish/{wishId}")
    public WishResponse findWishById(@PathVariable Long wishId) {
        return notificationService.findWishById(wishId);
    }

    @Operation(summary = "Find holiday by id", description = "The user can find holiday")
    @GetMapping("holiday/{holidayId}")
    public HolidayResponse findHolidayById(@PathVariable Long holidayId) {
        return notificationService.findHolidayById(holidayId);
    }

    @Operation(summary = "Get all notifications", description = "User can get all notifications")
    @GetMapping
    public List<NotificationResponse> findAll() {
        return userService.getAllNotifications();
    }
}
