package kg.giftlist.giftlist.db.repositories;

import kg.giftlist.giftlist.db.models.Category;
import kg.giftlist.giftlist.dto.subCategories.SubCategoriesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}