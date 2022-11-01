package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.categories.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAllCategories();

}
