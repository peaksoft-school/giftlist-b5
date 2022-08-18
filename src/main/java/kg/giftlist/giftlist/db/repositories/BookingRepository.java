package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select g from User u join u.booking.gifts g where u.id=?1")
    List<Gift> getAllUserBookingGifts(Long userId);

    @Query("select w from User u join u.booking.wishes w where u.id=?1")
    List<Wish> getAllUserBookingWishes(Long userId);

}