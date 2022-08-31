package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.notification.NotificationResponse;

import java.util.List;

public interface NotificationService {

    NotificationResponse findById(Long notificationId);

    List<NotificationResponse> findAll();

}
