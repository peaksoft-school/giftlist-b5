package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}