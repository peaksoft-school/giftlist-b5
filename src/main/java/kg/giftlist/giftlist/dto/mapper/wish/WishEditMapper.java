package kg.giftlist.giftlist.dto.mapper.wish;

import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.Wish;
import kg.giftlist.giftlist.repositories.UserRepository;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class WishEditMapper {

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

        return wish;
    }

    public void update(Wish wish, WishRequest wishRequest) {

        wish.setGiftName(wishRequest.getGiftName());

        wish.setGiftLink(wishRequest.getGiftLink());

        wish.setGiftPhoto(wishRequest.getPhoto());

        wish.setDescription(wishRequest.getDescription());

        wish.setWishDate(wishRequest.getWishDate());
    }
}
