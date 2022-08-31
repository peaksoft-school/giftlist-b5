package kg.giftlist.giftlist.dto.mapper.wish;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.db.models.Wish;
import org.springframework.stereotype.Component;

@Component
public class WishEditMapper {

    public Wish create(WishRequest request) {

        if (request == null) {
            return null;
        }
        Wish wish = new Wish();
        wish.setWishName(request.getWishName());
        wish.setWishLink(request.getWishLink());
        wish.setDescription(request.getDescription());
        wish.setWishPhoto(request.getPhoto());
        wish.setWishDate(request.getWishDate());
        return wish;
    }

    public void update(Wish wish, WishRequest wishRequest) {

        wish.setWishName(wishRequest.getWishName());
        wish.setWishLink(wishRequest.getWishLink());
        wish.setWishPhoto(wishRequest.getPhoto());
        wish.setDescription(wishRequest.getDescription());
        wish.setWishDate(wishRequest.getWishDate());
    }
}
