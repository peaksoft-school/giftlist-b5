package kg.giftlist.giftlist.dto.mapper.notification;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;

import java.util.ArrayList;
import java.util.List;

public class NotificationViewMapper {

    public NotificationResponse viewAddGiftNotifications(Notification notification) {

        return new NotificationResponse(
                notification.getId(),
                notification.getUser().getPhoto(),
                notification.getUser().getId(),
                notification.getUser().getFirstName(),
                notification.getUser().getLastName(),
                notification.getNotificationStatus(),
                notification.getGift().getId(),
                notification.getGift().getName(),
                notification.getCreatedAt(),
                notification.getIsRead()
        );
    }

    public NotificationResponse viewAddWishNotifications(Notification notification) {

        return new NotificationResponse(
                notification.getId(),
                notification.getUser().getPhoto(),
                notification.getUser().getId(),
                notification.getUser().getFirstName(),
                notification.getUser().getLastName(),
                notification.getNotificationStatus(),
                notification.getWish().getId(),
                notification.getWish().getGiftName(),
                notification.getCreatedAt(),
                notification.getIsRead()
        );
    }

    public NotificationResponse viewAddHolidayNotifications(Notification notification) {

        return new NotificationResponse(
                notification.getId(),
                notification.getUser().getPhoto(),
                notification.getUser().getId(),
                notification.getUser().getFirstName(),
                notification.getUser().getLastName(),
                notification.getNotificationStatus(),
                notification.getHoliday().getId(),
                notification.getHoliday().getName(),
                notification.getCreatedAt(),
                notification.getIsRead()
        );
    }

    public NotificationResponse viewBookingGiftNotification(Notification notification) {

        return new NotificationResponse(
                notification.getId(),
                notification.getGift().getId(),
                notification.getGift().getPhoto(),
                notification.getGift().getName(),
                notification.getNotificationStatus(),
                notification.getUser().getId(),
                notification.getUser().getFirstName(),
                notification.getUser().getLastName(),
                notification.getCreatedAt(),
                notification.getIsRead()
        );
    }

    public NotificationResponse viewBookingWishNotification(Notification notification) {

        return new NotificationResponse(
                notification.getId(),
                notification.getWish().getId(),
                notification.getWish().getGiftPhoto(),
                notification.getWish().getGiftName(),
                notification.getNotificationStatus(),
                notification.getUser().getId(),
                notification.getUser().getFirstName(),
                notification.getUser().getLastName(),
                notification.getCreatedAt(),
                notification.getIsRead()
        );
    }

    public NotificationResponse viewRequestToFriendNotification(Notification notification) {

        return new NotificationResponse(
                notification.getId(),
                notification.getUser().getId(),
                notification.getUser().getPhoto(),
                notification.getUser().getFirstName(),
                notification.getUser().getLastName(),
                notification.getNotificationStatus(),
                notification.getCreatedAt(),
                notification.getIsRead()
        );
    }

    public List<NotificationResponse> view(List<Notification> notifications) {

        List<NotificationResponse> responses = new ArrayList<>();
        for (Notification notification : notifications) {
            responses.add(viewAddGiftNotifications(notification));
            responses.add(viewAddWishNotifications(notification));
            responses.add(viewAddHolidayNotifications(notification));
            responses.add(viewBookingGiftNotification(notification));
            responses.add(viewBookingWishNotification(notification));
            responses.add(viewRequestToFriendNotification(notification));
        }
        return responses;
    }
}
