package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.dto.notification.NotificationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.id = ?1")
    NotificationResponse findByNotificationId(Long id);

    @Query("select n from Notification n where n.gift.id = ?1")
    NotificationResponse findByGiftId(Long giftId);

    @Query("select n from Notification n where n.wish.id = ?1")
    NotificationResponse findByWishId(Long wishId);

    @Query("select n from Notification n where n.holiday.id = ?1")
    NotificationResponse findByHolidayId(Long holidayId);

    @Query("select n from Notification n where n.user.id = ?1")
    NotificationResponse findByUserId(Long userId);

    @Query("select n from Notification n where n.recipientId=?1")
    List<Notification> getAllNotifications(Long userId);

}
