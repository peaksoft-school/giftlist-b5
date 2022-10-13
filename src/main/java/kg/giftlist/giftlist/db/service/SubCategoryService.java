package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.subCategories.SubCategoriesResponse;

import java.util.List;

public interface SubCategoryService {

    List<SubCategoriesResponse> getAllSubCategories(Long categoryId);

}
