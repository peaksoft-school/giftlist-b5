package kg.giftlist.giftlist.dto.mapper.notification;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import kg.giftlist.giftlist.enums.NotificationStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationViewMapper {

    public NotificationResponse viewNotification(Notification notification) {

        NotificationResponse response = new NotificationResponse();
        response.setNotificationId(notification.getId());
        if (notification.getNotificationStatus().equals(NotificationStatus.REQUEST_TO_FRIEND)) {
            response.setUserId(notification.getUser().getId());
            response.setPhoto(notification.getUser().getPhoto());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
        } else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_GIFT)) {
            response.setPhoto(notification.getUser().getPhoto());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setWishOrGiftOrHolidayId(notification.getGift().getId());
            response.setWishOrGiftOrHolidayName(notification.getGift().getName());

        } else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_WISH)) {
            response.setPhoto(notification.getUser().getPhoto());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setWishOrGiftOrHolidayId(notification.getWish().getId());
            response.setWishOrGiftOrHolidayName(notification.getWish().getWishName());

        } else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_HOLIDAY)) {
            response.setPhoto(notification.getUser().getPhoto());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setWishOrGiftOrHolidayId(notification.getHoliday().getId());
            response.setWishOrGiftOrHolidayName(notification.getHoliday().getName());

        } else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_GIFT_BOOKING)) {
            response.setWishOrGiftOrHolidayId(notification.getGift().getId());
            response.setPhoto(notification.getGift().getPhoto());
            response.setWishOrGiftOrHolidayName(notification.getGift().getName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());

        } else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_WISH_BOOKING)) {
            response.setWishOrGiftOrHolidayId(notification.getWish().getId());
            response.setPhoto(notification.getWish().getWishPhoto());
            response.setWishOrGiftOrHolidayName(notification.getWish().getWishName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
        }
        response.setCreatedAt(notification.getCreatedAt());
        response.setRead(notification.isRead());
        return response;
    }

    public List<NotificationResponse> getAll(List<Notification> notifications) {
        List<NotificationResponse> responses = new ArrayList<>();
        for (Notification notification : notifications) {
            responses.add(viewNotification(notification));
        }
        return responses;
    }
}