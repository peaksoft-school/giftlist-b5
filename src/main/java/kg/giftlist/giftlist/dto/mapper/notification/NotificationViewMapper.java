package kg.giftlist.giftlist.dto.mapper.notification;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import kg.giftlist.giftlist.enums.NotificationStatus;
import org.springframework.stereotype.Component;

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
            response.setOthersId(notification.getGift().getId());
            response.setOthersName(notification.getGift().getName());

        } else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_WISH)) {
            response.setPhoto(notification.getUser().getPhoto());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setOthersId(notification.getWish().getId());
            response.setOthersName(notification.getWish().getGiftName());

        } else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_HOLIDAY)) {
            response.setPhoto(notification.getUser().getPhoto());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setOthersId(notification.getHoliday().getId());
            response.setOthersName(notification.getHoliday().getName());

        }
        else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_GIFT_BOOKING)) {
            response.setOthersId(notification.getGift().getId());
            response.setPhoto(notification.getGift().getPhoto());
            response.setOthersName(notification.getGift().getName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());

        }
        else if (notification.getNotificationStatus().equals(NotificationStatus.ADD_WISH_BOOKING)) {
            response.setOthersId(notification.getWish().getId());
            response.setPhoto(notification.getWish().getGiftPhoto());
            response.setOthersName(notification.getWish().getGiftName());
            response.setNotificationStatus(notification.getNotificationStatus());
            response.setUserId(notification.getUser().getId());
            response.setUserFirstName(notification.getUser().getFirstName());
            response.setUserLastName(notification.getUser().getLastName());
        }
        response.setCreatedAt(notification.getCreatedAt());
        response.setRead(notification.isRead());
        return response;
    }
}