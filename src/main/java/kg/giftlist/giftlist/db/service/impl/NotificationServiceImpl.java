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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;
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
//
//    public List<NotificationResponse> getAll() {
//        return viewMapper.getAll(notificationRepository.
//                getAllNotifications(getAuthenticatedUser().getId()));
//    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }


    public User findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("user with id = %s does not exists", userId)
                ));
    }
}
