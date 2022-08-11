package kg.giftlist.giftlist.repositories;

import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

    @Query("select g " +
            "from Gift g")
    List<GiftResponse> getGiftByBooking();
}