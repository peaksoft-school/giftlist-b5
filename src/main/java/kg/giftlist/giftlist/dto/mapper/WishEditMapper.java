package kg.giftlist.giftlist.dto.mapper;

import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.models.Wish;
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

        wish.setWishDate(request.getWishDate());

        wish.setGiftPhoto(request.getPhoto());

        return wish;

    }

    public void update(Wish wish,
                       WishRequest wishRequest) {

        wish.setGiftName(wishRequest.getGiftName());

        wish.setGiftLink(wishRequest.getGiftLink());

        wish.setGiftPhoto(wishRequest.getPhoto());

        wish.setDescription(wishRequest.getDescription());

        wish.setWishDate(wishRequest.getWishDate());

    }
}
