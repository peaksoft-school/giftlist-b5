package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query("select h from Holiday h where h.name = ?1")
    Optional<Holiday> findByName(String holidayName);

}