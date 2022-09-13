package kg.giftlist.giftlist.dto.mapper.wish;
import kg.giftlist.giftlist.db.models.Complaint;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import kg.giftlist.giftlist.dto.wish.UserWishResponse;
import kg.giftlist.giftlist.dto.wish.WishCardResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.enums.AddWishStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.ForbiddenException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WishViewMapper {

    private final UserRepository userRepository;

    public WishCardResponse viewWish(Wish wish) {

        if (wish == null) {
            return null;
        }
        WishCardResponse wishCardResponse = new WishCardResponse();
        wishCardResponse.setWishId(wish.getId());
        wishCardResponse.setWishName(wish.getWishName());
        wishCardResponse.setPhoto(wish.getWishPhoto());
        wishCardResponse.setWishLink(wish.getWishLink());
        wishCardResponse.setDescription(wish.getDescription());
        wishCardResponse.setWishDate(wish.getWishDate());
        wishCardResponse.setIsBlock(wish.getIsBlock());
        wishCardResponse.setComplaints(viewAllComplaints(wish.getComplaints()));
        wishCardResponse.setHoliday(wish.getHoliday());
        wishCardResponse.setBooking(wish.getBooking());
        User user = getAuthenticatedUser();

        if (user.getWishes().stream().anyMatch(wish1 -> wish1.getWishName().equals(wish.getWishName()))) {
            wishCardResponse.setAddWishStatus(AddWishStatus.ADDED);
        }else {
            wishCardResponse.setAddWishStatus(AddWishStatus.NOT_ADD);
          }
        return wishCardResponse;
    }

    public UserWishResponse viewUserWish(User user){
        if (user == null) {
            return null;
        }
        UserWishResponse userWishResponse = new UserWishResponse();
        userWishResponse.setUserId(user.getId());
        userWishResponse.setFirstName(user.getFirstName());
        userWishResponse.setLastName(user.getLastName());
        userWishResponse.setPhoto(user.getPhoto());
        if (user.getUserInfo()==null || user.getUserInfo().getPhoneNumber()==null){
            return userWishResponse;
        }
        userWishResponse.setPhoneNumber(user.getUserInfo().getPhoneNumber());
        return userWishResponse;
    }

    public WishResponse viewCommonWishCard(User user, Wish wish) {
        User user1 = getAuthenticatedUser();
        WishResponse response = new WishResponse();
        if (wish == null) {
            return null;
        }
        if (wish.getIsBlock().equals(true) && !wish.getUser().equals(user1)) {
            return null;
        }
        response.setOwnerUser(viewUserWish(user));
        response.setWish(viewWish(wish));
        if (wish.getBooking()==null || wish.getBooking().getUser()==null) {
            return response;
        }
        response.setBookedUser(viewUserWish(wish.getBooking().getUser()));
        return response;
    }

    public List<WishResponse> getAllWishes(List<Wish> wishes) {
        List<WishResponse> wishResponses = new ArrayList<>();
        for (Wish wish : wishes) {
            User user = wish.getUser();
            wishResponses.add(viewCommonWishCard(user,wish));
        }
        return wishResponses;
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }

    public ComplaintResponse viewComplaints(Complaint complaint) {
        if (complaint == null) {
            return null;
        }
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setComplaintId(complaint.getId());
        complaintResponse.setText(complaint.getText());
        complaintResponse.setFromUser(viewUserWish(complaint.getFromUser()));
        return complaintResponse;
    }

    public List<ComplaintResponse> viewAllComplaints(List<Complaint> complaints) {
        if (complaints == null) {
            return null;
        }
        List<ComplaintResponse> complaintResponses = new ArrayList<>();
        for (Complaint complaint : complaints) {
            complaintResponses.add(viewComplaints(complaint));
        }
        return complaintResponses;
    }
}
