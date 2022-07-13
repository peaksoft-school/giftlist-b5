package kg.giftlist.giftlist.repositories;

import kg.giftlist.giftlist.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
}