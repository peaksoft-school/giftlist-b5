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
    private boolean isRead;

    public NotificationResponse() {
    }

    public NotificationResponse(
            Long notificationId, Long othersId, String othersPhoto, String nameOfGiftOrWish,
            NotificationStatus notificationStatus, Long userId,
            String userFirstName, String userLastName,
            LocalDate createdAt, boolean isRead) {

        this.notificationId = notificationId;
        this.wishOrGiftOrHolidayId = othersId;
        this.photo = othersPhoto;
        this.wishOrGiftOrHolidayName = nameOfGiftOrWish;
        this.notificationStatus = notificationStatus;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    public NotificationResponse(Long id, Long id1, String photo, NotificationStatus notificationStatus, String firstName, String lastName, LocalDate createdAt, boolean read) {
    }

    public NotificationResponse(Long id, String photo, Long id1, String firstName, String lastName, NotificationStatus notificationStatus, Long id2, String name, LocalDate createdAt, boolean read) {

    }
}
