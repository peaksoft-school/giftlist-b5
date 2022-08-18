package kg.giftlist.giftlist.db.service.impl;


import kg.giftlist.giftlist.db.models.Category;
import kg.giftlist.giftlist.db.models.SubCategory;
import kg.giftlist.giftlist.db.repositories.CategoryRepository;
import kg.giftlist.giftlist.db.service.SubCategoryService;
import kg.giftlist.giftlist.dto.subCategories.SubCategoriesResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<SubCategoriesResponse> getAllSubCategories(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(
                "category with id = " + categoryId + " not found!"
        ));
        List<SubCategory> subCategories = category.getSubCategories();
        List<SubCategoriesResponse> subCategoryResponses = new ArrayList<>();

        for (SubCategory subCategory : subCategories) {
            SubCategoriesResponse subCategoryResponse = new SubCategoriesResponse();
            subCategoryResponse.setId(subCategory.getId());
            subCategoryResponse.setName(subCategory.getName());
            subCategoryResponses.add(subCategoryResponse);
        }
        return subCategoryResponses;
    }
}
