package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.repositories.NotificationRepository;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl {

    private final NotificationRepository notificationRepository;

    public NotificationResponse findById(Long notificationId) {
        return notificationRepository.findByNotificationId(notificationId);
    }

    public List<NotificationResponse> findAll() {
        return notificationRepository.findAllNotifications();
    }
}
