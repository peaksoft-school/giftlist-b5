package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
}