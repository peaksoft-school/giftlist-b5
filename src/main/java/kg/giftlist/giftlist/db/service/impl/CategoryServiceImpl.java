package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.Category;
import kg.giftlist.giftlist.db.repositories.CategoryRepository;
import kg.giftlist.giftlist.db.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
