package kg.giftlist.giftlist.repositories;

import kg.giftlist.giftlist.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.ValidationException;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    @Query("select h from Holiday h where h.name = ?1")
    Optional<Holiday> findByName(String holidayName);


//    default Holiday findByName(String holidayName){
//        Optional<Holiday> optionalHoliday = getByName(holidayName);
//        if (optionalHoliday.isEmpty()){
//            throw new ValidationException("not found by Holiday : "+holidayName);
//        }
//        return optionalHoliday.get();
//    }
}