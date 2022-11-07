package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}