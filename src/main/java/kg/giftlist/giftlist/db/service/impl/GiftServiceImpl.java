package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.*;
import kg.giftlist.giftlist.db.repositories.*;
import kg.giftlist.giftlist.db.service.GiftService;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.gift.mapper.GiftEditMapper;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.enums.NotificationStatus;
import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ForbiddenException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;
    private final GiftViewMapper giftViewMapper;
    private final GiftEditMapper giftEditMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ComplaintRepository complaintRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public GiftResponse create(GiftRequest request) {
        User user = getAuthenticatedUser();


        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                new NotFoundException("Category with id: " + request.getCategoryId() + " not found"));
        Gift gift = giftEditMapper.create(request);
        gift.setCategory(category);
        SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId()).orElseThrow(() ->
                new NotFoundException("SubCategory with id: " + request.getSubCategoryId() + " not found"));
        if (subCategory.getCategory().getId().equals(category.getId())) {
            gift.setSubCategory(subCategory);
        } else {
            throw new NotFoundException("SubCategory with id: " + request.getSubCategoryId() + " not found");
        }
        if (request.getPhoto()==null){
            gift.setPhoto("https://giftlist-bucket.s3.amazonaws.com/1661860640125charity-default-image.jpg");
        }else {
            gift.setPhoto(request.getPhoto());
        }
        user.setGifts(List.of(gift));
        gift.setUser(user);
        gift.setCreatedAt(LocalDate.now());
        giftRepository.save(gift);


        for (User fr : user.getFriends()) {
            Notification notification = new Notification();
            notification.setNotificationStatus(NotificationStatus.ADD_GIFT);
            notification.setCreatedAt(LocalDate.now());
            notification.setUser(user);
            notification.setGift(gift);
            notification.setRecipientId(fr.getId());
            user.addNotification(notification);

            notificationRepository.save(notification);
        }

        return giftViewMapper.viewCommonGiftCard(user, gift);
    }

    @Override
    @Transactional
    public GiftResponse update(Long giftId, GiftRequest request) {
        User user = getAuthenticatedUser();
        Gift gift = findById(giftId);
        if (gift.getUser().equals(user)){
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() ->
                    new NotFoundException("Category with id: " + request.getCategoryId() + " not found"));
            gift.setCategory(category);
            SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId()).orElseThrow(() ->
                    new NotFoundException("SubCategory with id: " + request.getSubCategoryId() + " not found"));
            if (category.getSubCategories().contains(subCategory)) {
                gift.setSubCategory(subCategory);
            }else {
                throw new NotFoundException("SubCategory with id: " + request.getSubCategoryId() + " not found");
            }
            giftEditMapper.update(gift,request);
        }
        return giftViewMapper.viewCommonGiftCard(user,gift);
    }

    @Override
    public Gift findById(Long id) {
        return giftRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Gift with id: " + id + " not found"));
    }

    @Override
    public SimpleResponse deleteById(Long giftId) {
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException("Wish with id = " + giftId + " not found!"));
        if (gift.getBooking()!=null) {
            User user = gift.getBooking().getUser();
            user.getBooking().getGifts().remove(gift);
            gift.setBooking(null);
        }
        List<Complaint> complaints = complaintRepository.findAll();
        complaints.removeIf(c -> Objects.equals(gift.getComplaints(), c));
        giftRepository.deleteById(giftId);
        return new SimpleResponse("Deleted!", "Gift successfully deleted!");
    }

    @Override
    public List<GiftResponse> getAll() {
        User user = getAuthenticatedUser();
        return giftViewMapper.getAllGifts(giftRepository.getAllUserGifts(user.getId()));
    }

    @Override
    public List<GiftResponse> filter(String search,Status status,Long categoryId,Long subCategoryId) {
        return giftViewMapper.getAllGifts(giftRepository.filterGift(search,status,categoryId,subCategoryId));
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
