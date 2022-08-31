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
    private Long othersId;
    private String photo;
    private String othersName;
    private NotificationStatus notificationStatus;
    private String userFirstName;
    private String userLastName;
    private LocalDate createdAt;
    private Boolean isRead;

    public NotificationResponse() {
    }

    public NotificationResponse(
            Long notificationId, String userPhoto, Long userId, String userFirstName,
            String userLastName, NotificationStatus notificationStatus, Long wishOrGiftOrHolidayId, String othersName,
            LocalDate createdAt, Boolean isRead) {

        this.notificationId = notificationId;
        this.photo = userPhoto;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.notificationStatus = notificationStatus;
        this.othersId = wishOrGiftOrHolidayId;
        this.othersName = othersName;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    public NotificationResponse(
            Long notificationId, Long othersId, String othersPhoto, String nameOfGiftOrWish,
            NotificationStatus notificationStatus, Long userId,
            String userFirstName, String userLastName,
            LocalDate createdAt, Boolean isRead) {

        this.notificationId = notificationId;
        this.othersId = othersId;
        this.photo = othersPhoto;
        this.othersName = nameOfGiftOrWish;
        this.notificationStatus = notificationStatus;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    public NotificationResponse(
            Long notificationId, Long userId, String userPhoto, String userFirstName, String userLastName,
            NotificationStatus notificationStatus,
            LocalDate createdAt, Boolean isRead) {

        this.notificationId = notificationId;
        this.userId = userId;
        this.photo = userPhoto;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.notificationStatus = notificationStatus;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }
}
