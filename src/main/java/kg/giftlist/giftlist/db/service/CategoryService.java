package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.db.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategories();
}
