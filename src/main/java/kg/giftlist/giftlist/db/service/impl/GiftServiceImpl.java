package kg.giftlist.giftlist.db.service.impl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.gift.mapper.GiftEditMapper;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.db.models.Category;
import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.SubCategory;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.CategoryRepository;
import kg.giftlist.giftlist.db.repositories.GiftRepository;
import kg.giftlist.giftlist.db.repositories.SubCategoryRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.ForbiddenException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {

    private final GiftRepository  giftRepository;
    private final GiftViewMapper giftViewMapper;
    private final GiftEditMapper giftEditMapper;
    private final UserRepository  userRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public GiftResponse create(GiftRequest request) {
        User user = getAuthenticatedUser();
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                new NotFoundException("Category with id: " + request.getCategoryId() + "not found"));
        Gift gift = giftEditMapper.create(request);
        gift.setCategory(category);
        SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId()).orElseThrow(() ->
                new NotFoundException("SubCategory with id: " + request.getSubCategoryId() + "not found"));
        category.setSubCategories(List.of(subCategory));
        user.setGifts(List.of(gift));
        gift.setUser(user);
        gift.setCreatedAt(LocalDate.now());
        giftRepository.save(gift);
        return giftViewMapper.viewCommonGiftCard(user,gift);
    }

    @Override
    @Transactional
    public GiftResponse update(Long giftId, GiftRequest request) {
        User user = getAuthenticatedUser();
        Gift gift = findById(giftId);
        if (gift.getUser().equals(user)){
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                    new NotFoundException("Category with id: " + request.getCategoryId() + "not found"));
            gift.setCategory(category);
            SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId()).orElseThrow(() ->
                    new NotFoundException("SubCategory with id: " + request.getSubCategoryId() + "not found"));
            category.setSubCategories(List.of(subCategory));
            giftEditMapper.update(gift,request);
        }
        return giftViewMapper.viewCommonGiftCard(user,gift);
    }

    @Override
    public Gift findById(Long id) {
        return giftRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Gift with id: " + id + "not found"));
    }

    @Override
    public SimpleResponse deleteById(Long giftId) {
        boolean exists = giftRepository.existsById(giftId);
        if (!exists) {
            throw new NotFoundException("Gift with id = " + giftId + " not found!");
        }
        giftRepository.deleteById(giftId);
        return new SimpleResponse("Deleted!", "Gift successfully deleted!");
    }

    @Override
    public List<GiftResponse> getAll() {
        return giftViewMapper.getAllGifts(giftRepository.findAll());
    }

    public GiftResponse getGiftById(Long giftId) {
        Gift gift = findById(giftId);
        User user = gift.getUser();
        return giftViewMapper.viewCommonGiftCard(user,gift);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }

}
