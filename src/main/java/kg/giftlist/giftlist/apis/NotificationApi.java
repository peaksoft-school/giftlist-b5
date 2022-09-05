package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.*;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import kg.giftlist.giftlist.dto.user.UserResponse;
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
    private final GiftServiceImpl giftService;
    private final WishServiceImpl wishService;
    private final HolidayServiceImpl holidayService;

    @Operation(summary = "Get user", description = "User can get user by id")
    @GetMapping("/user/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserByUserId(id);
    }

    @Operation(summary = "Get user", description = "User can get user by username")
    @GetMapping("/user/{username}")
    public UserResponse findUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @Operation(summary = "Find gift by id", description = "The user can find gift")
    @GetMapping("gift/{giftId}")
    public GiftResponse findGiftById(@PathVariable Long giftId) {
        return giftService.getGiftById(giftId);
    }

    @Operation(summary = "Find wish by id", description = "The user can find wish")
    @GetMapping("wish/{wishId}")
    public WishResponse findWishById(@PathVariable Long wishId) {
        return wishService.findById(wishId);
    }

    @Operation(summary = "Find holiday by id", description = "The user can find holiday")
    @GetMapping("holiday/{holidayId}")
    public HolidayResponse findHolidayById(@PathVariable Long holidayId) {
        return holidayService.findById(holidayId);
    }

    @Operation(summary = "Get all notifications", description = "User can get all notifications")
    @GetMapping
    public List<NotificationResponse> findAll() {
        return userService.getAllNotifications();
    }
}
