package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import kg.giftlist.giftlist.db.service.impl.ComplaintServiceImpl;
import kg.giftlist.giftlist.db.service.impl.GiftServiceImpl;
import kg.giftlist.giftlist.db.service.impl.HolidayServiceImpl;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import kg.giftlist.giftlist.db.service.impl.WishServiceImpl;

import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import kg.giftlist.giftlist.dto.user_friends.UserFriendProfileResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notifications")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Notification API", description = "User with role \"User\", \"ADMIN\" can see own notifications")
public class NotificationApi {

    private final UserServiceImpl userService;
    private final GiftServiceImpl giftService;
    private final WishServiceImpl wishService;
    private final HolidayServiceImpl holidayService;
    private final ComplaintServiceImpl complaintService;

    @Operation(summary = "Get user by id", description = "User and Admin can get user by id")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("user/{userId}")
    public UserFriendProfileResponse findUserById(@PathVariable Long userId) {
        return userService.findUserByUserId(userId);
    }

    @Operation(summary = "Find gift by id", description = "User and Admin can find gift")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("gift/{giftId}")
    public GiftResponse findGiftById(@PathVariable Long giftId) {
        return giftService.getGiftById(giftId);
    }

    @Operation(summary = "Get complaint wish by id", description = "Admin can get wish by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("complaint/{wishId}")
    public WishResponse getComplaintWishById(@PathVariable Long wishId) {
        return complaintService.getComplaintWishById(wishId);
    }

    @Operation(summary = "Get complaint gift by id", description = "Admin can get gift by id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("complaint/{giftId}")
    public GiftResponse getComplaintGiftById(@PathVariable Long giftId) {
        return complaintService.getComplaintGiftById(giftId);
    }

    @Operation(summary = "Find wish by id", description = "User and Admin can find wish")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("wish/{wishId}")
    public WishResponse findWishById(@PathVariable Long wishId) {
        return wishService.findById(wishId);
    }

    @Operation(summary = "Find holiday by id", description = "User and Admin can find holiday")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("holiday/{holidayId}")
    public HolidayResponse findHolidayById(@PathVariable Long holidayId) {
        return holidayService.findById(holidayId);
    }

    @Operation(summary = "Get all notifications", description = "User and Admin can get all notifications")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping
    public List<NotificationResponse> findAll() {
        return userService.getAllNotifications();
    }

    @Operation(summary = "Mark as read", description = "User and Admin can mark as read return notifications list")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PutMapping("mark-as-read")
    public List<NotificationResponse> markAsRead() {
        return userService.markAsRead();
    }

}
