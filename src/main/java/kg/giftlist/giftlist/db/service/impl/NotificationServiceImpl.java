package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.NotificationRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.dto.mapper.notification.NotificationViewMapper;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationViewMapper viewMapper;

    public List<NotificationResponse> markAsRead(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        for (Notification notification : user.getNotifications()) {
            notification.setRead(true);
        }
        notificationRepository.saveAll(user.getNotifications());
        return viewMapper.getAll(user.getNotifications());
    }
}
