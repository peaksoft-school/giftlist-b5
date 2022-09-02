package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.NotificationServiceImpl;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
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

    @Operation(summary = "Get notification", description = "User can get notification")
    @GetMapping("/{id}")
    public NotificationResponse findById(@PathVariable Long id) {
        return notificationService.findById(id);
    }

    @Operation(summary = "Get all notifications", description = "User can get all notifications")
    @GetMapping
    public List<NotificationResponse> findAll() {
        return notificationService.getAll();
    }
}
