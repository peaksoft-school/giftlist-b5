package kg.giftlist.giftlist.repositories;

import kg.giftlist.giftlist.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("select w from Wish w where upper(w.giftName) like %?1% order by w.giftName asc")
    List<Wish> searchByGiftName(String keyword);
}