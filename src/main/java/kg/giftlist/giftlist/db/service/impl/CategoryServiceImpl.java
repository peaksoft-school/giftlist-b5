package kg.giftlist.giftlist.db.service.impl;
import kg.giftlist.giftlist.db.models.Category;
import kg.giftlist.giftlist.db.repositories.CategoryRepository;
import kg.giftlist.giftlist.db.service.CategoryService;
import kg.giftlist.giftlist.dto.categories.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> newCategories = new ArrayList<>();
        for (Category category : categories) {
            newCategories.add(new CategoryResponse(category));
        }
        return newCategories;
    }
}
