package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.gift.mapper.GiftEditMapper;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.models.Category;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.SubCategory;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.CategoryRepository;
import kg.giftlist.giftlist.repositories.GiftRepository;
import kg.giftlist.giftlist.repositories.SubCategoryRepository;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.services.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {

    private final GiftRepository  giftRepository;
    private final GiftViewMapper giftViewMapper;
    private final GiftEditMapper giftEditMapper;
    private final UserServiceImpl  userService;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public GiftResponse create(GiftRequest request) {
        User user = userService.getAuthenticatedUser();
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                new NotFoundException("Category with id: " + request.getCategoryId() + "not found"));
        Gift gift = giftEditMapper.create(request);
        gift.setCategory(category);
        SubCategory subCategory = subCategoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                new NotFoundException("SubCategory with id: " + request.getSubCategoryId() + "not found"));
        category.setSubCategories(List.of(subCategory));
        user.setGifts(List.of(gift));
        gift.setCreatedAt(LocalDate.now());
        giftRepository.save(gift);
        return giftViewMapper.viewCommonGiftCard(user,gift);
    }

    @Override
    public GiftResponse update(Long id, GiftRequest request) {

        return null;
    }

    @Override
    public Gift findById(Long id) {
        Gift gift = giftRepository.findById(id).orElseThrow(() ->
                new NotFoundException("SubCategory with id: " + id + "not found"));
        return gift;
    }

    @Override
    public GiftResponse deleteById(Long id) {
        Gift gift = giftRepository.getById(id);
        giftRepository.delete(gift);
        return null;
    }

    @Override
    public List<GiftResponse> getAll() {
        return null;
    }



}
