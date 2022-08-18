package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import java.util.List;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

    @Query("select g from User u join u.gifts g where u.id=?1")
    List<Gift> getAllUserGifts(Long userId);
}