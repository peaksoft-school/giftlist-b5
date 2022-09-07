package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.recipientId=?1")
    List<Notification> getAllNotifications(Long userId);
}
