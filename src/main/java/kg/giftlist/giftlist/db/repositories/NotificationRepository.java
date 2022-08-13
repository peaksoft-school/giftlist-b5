package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}