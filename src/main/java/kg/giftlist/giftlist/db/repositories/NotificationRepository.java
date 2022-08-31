package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByGiftId(Long giftId);
    Optional<Notification> findByWishId(Long wishId);
    Optional<Notification> findByHolidayId(Long holidayId);
    Optional<Notification> findByUserId(Long userId);

    @Query("select n from Notification n order by n.isRead asc")
    List<NotificationResponse> findAllNotifications();
}
