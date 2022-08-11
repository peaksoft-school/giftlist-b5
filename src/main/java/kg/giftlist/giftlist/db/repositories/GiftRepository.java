package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
}