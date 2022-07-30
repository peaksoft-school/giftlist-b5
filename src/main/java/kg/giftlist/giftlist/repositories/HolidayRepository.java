package kg.giftlist.giftlist.repositories;

import kg.giftlist.giftlist.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {


    @Query("select h from Holiday h where upper(h.name) like %?1% order by h.name asc")
    List<Holiday> searchByName(String keyword);
}