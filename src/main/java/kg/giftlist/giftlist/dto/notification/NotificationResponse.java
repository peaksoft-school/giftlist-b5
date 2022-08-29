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
    private Long withIdOrGiftIdOrBookingId;
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
            case BOOKED_GIFT:
                this.withIdOrGiftIdOrBookingId = notification.getGiftBooking().getId();
                this.photo = notification.getGiftBooking().getPhoto();
                this.name = notification.getGiftBooking().getName();
            case ADD_GIFT:
                this.withIdOrGiftIdOrBookingId = notification.getGift().getId();
                this.photo = notification.getGift().getPhoto();
                this.name = notification.getGift().getName();
            case ADD_WISH:
                this.withIdOrGiftIdOrBookingId = notification.getWish().getId();
                this.photo = notification.getWish().getGiftPhoto();
                this.name = notification.getWish().getGiftName();
            case ADD_HOLIDAY:
                this.withIdOrGiftIdOrBookingId = notification.getHoliday().getId();
                this.photo = notification.getHoliday().getPhoto();
                this.name = notification.getHoliday().getName();
            case REQUEST_TO_FRIEND:
                this.photo = notification.getUser().getPhoto();
                this.name = notification.getUser().getFirstName();
        }
    }
}
