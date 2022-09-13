package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("select w from User u join u.wishes w where u.id=?1")
    List<Wish> getAllUserWishes(Long userId);

    @Query("select w from User u join u.friends f join f.wishes w where u.id=?1 order by w.createdAt DESC")
    List<Wish> getAllFriendWishes(Long userId);

    @Query("select aw from Wish aw order by aw.createdAt DESC")
    List<Wish> getAllWishes();

    @Query("select w from Wish w where w.complaints.size>0")
    List<Wish> getAllComplaintsWishes();
}