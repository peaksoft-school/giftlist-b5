package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query("select h from Holiday h where h.name = ?1")
    Optional<Holiday> findByName(String holidayName);

    @Query("select h from User u join u.holidays h where u.id=?1")
    List<Holiday> getAllUserHolidays(Long userId);
}