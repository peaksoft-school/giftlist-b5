package kg.giftlist.giftlist.dto.mapper.wish;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.dto.wish.UserWishResponse;
import kg.giftlist.giftlist.dto.wish.WishCardResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.db.models.Wish;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class WishViewMapper {

    public WishCardResponse viewWish(Wish wish) {

        if (wish == null) {
            return null;
        }
        return WishCardResponse.builder()
                .id(wish.getId())
                .giftName(wish.getGiftName())
                .giftLink(wish.getGiftLink())
                .description(wish.getDescription())
                .photo(wish.getGiftPhoto())
                .wishDate(wish.getWishDate())
                .holidayName(wish.getHolidayName())
                .booking(wish.getBooking())
                .build();
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
        if (wish == null) {
            return null;
        }
        WishResponse response = new WishResponse();
        response.setUser(viewUserWish(user));
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
}
