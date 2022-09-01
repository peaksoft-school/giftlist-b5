package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.NotificationRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.dto.mapper.notification.NotificationViewMapper;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl {

    private final UserRepository userRepository;
    private final NotificationViewMapper viewMapper;

    private final NotificationRepository notificationRepository;

    public NotificationResponse findById(Long notificationId) {
        return notificationRepository.findByNotificationId(notificationId);
    }

    public List<NotificationResponse> getAllNotifications() {
        User user = getAuthenticatedUser();
        List<NotificationResponse> responses = new ArrayList<>();
        for (Notification notification : notificationRepository.getAllByUserId(user.getId())) {
            responses.add(viewMapper.viewNotification(notification));
        }
        return responses;
    }


    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }
}
