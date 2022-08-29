package kg.giftlist.giftlist.dto.notification;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NotificationResponse {

    private Long notificationId;
    private Long userId;
    private Long wishIdOrGiftIdOrBookingId;
    private String photo;
    private String name;
    private NotificationStatus notificationStatus;
    private String userName;
    private LocalDate createdAt;

    public NotificationResponse() {
    }

    public NotificationResponse(Notification notification) {
        this.notificationId = notification.getId();
        this.userId = notification.getUser().getUserInfo().getId();
        switch (notification.getNotificationStatus()) {
            case ADD_GIFT_BOOKING:
                this.wishIdOrGiftIdOrBookingId = notification.getGiftBooking().getId();
            case ADD_GIFT:
                this.wishIdOrGiftIdOrBookingId = notification.getGift().getId();
                this.photo = notification.getGift().getPhoto();
                this.name = notification.getGift().getName();
            case ADD_WISH:
                this.wishIdOrGiftIdOrBookingId = notification.getWish().getId();
                this.photo = notification.getWish().getGiftPhoto();
                this.name = notification.getWish().getGiftName();
            case ADD_HOLIDAY:
                this.wishIdOrGiftIdOrBookingId = notification.getHoliday().getId();
                this.photo = notification.getHoliday().getPhoto();
                this.name = notification.getHoliday().getName();
            case REQUEST_TO_FRIEND:
                this.photo = notification.getUser().getPhoto();
                this.name = notification.getUser().getFirstName();
        }
    }
}
