package kg.giftlist.giftlist.dto.notification;

import kg.giftlist.giftlist.enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NotificationResponse {

    private Long notificationId;
    private Long userId;
    private Long wishOrGiftOrHolidayId;
    private String photo;
    private String wishOrGiftOrHolidayName;
    private NotificationStatus notificationStatus;
    private String userFirstName;
    private String userLastName;
    private LocalDate createdAt;
    private Long complaintId;
    private boolean isRead;

    public NotificationResponse() {
    }

}
