package kg.giftlist.giftlist.dto.mapper.wish;

import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.Wish;
import kg.giftlist.giftlist.repositories.UserRepository;
import org.springframework.stereotype.Component;


@Component
public class WishEditMapper {

    private final UserRepository userRepository;

    public WishEditMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Wish create(WishRequest request) {

        if (request == null) {

            return null;

        }

        Wish wish = new Wish();


        User user = new User();

        wish.setGiftName(request.getGiftName());

        wish.setGiftLink(request.getGiftLink());

        wish.setDescription(request.getDescription());

        wish.setGiftPhoto(request.getPhoto());

        wish.setWishDate(request.getWishDate());

        wish.setHolidayName(request.getHolidayName());

        wish.setUserId(user.getId());

        return wish;

    }

    public void update(Wish wish,
                       WishRequest wishRequest, User user) {

        wish.setGiftName(wishRequest.getGiftName());

        wish.setGiftLink(wishRequest.getGiftLink());

        wish.setGiftPhoto(wishRequest.getPhoto());

        wish.setDescription(wishRequest.getDescription());

        wish.setWishDate(wishRequest.getWishDate());

        wish.setHolidayName(wishRequest.getHolidayName());

        wish.setUserId(user.getId());

    }
}
