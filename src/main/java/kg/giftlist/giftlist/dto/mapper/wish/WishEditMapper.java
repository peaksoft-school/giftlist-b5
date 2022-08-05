package kg.giftlist.giftlist.dto.mapper.wish;

import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.Wish;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import org.springframework.stereotype.Component;


@Component
public class WishEditMapper {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public WishEditMapper(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Wish create(WishRequest request) {

        if (request == null) {

            return null;

        }

        Wish wish = new Wish();


        wish.setGiftName(request.getGiftName());

        wish.setGiftLink(request.getGiftLink());

        wish.setDescription(request.getDescription());

        wish.setGiftPhoto(request.getPhoto());

        wish.setWishDate(request.getWishDate());

        //wish.setHolidayName(request.getHolidayName());

       // wish.setUserId(userService.getAuthenticatedUser().getId());

        return wish;

    }

    public void update(Wish wish,
                       WishRequest wishRequest) {

        wish.setGiftName(wishRequest.getGiftName());

        wish.setGiftLink(wishRequest.getGiftLink());

        wish.setGiftPhoto(wishRequest.getPhoto());

        wish.setDescription(wishRequest.getDescription());

        wish.setWishDate(wishRequest.getWishDate());

        //wish.setHolidayName(wishRequest.getHolidayName());



    }
}
