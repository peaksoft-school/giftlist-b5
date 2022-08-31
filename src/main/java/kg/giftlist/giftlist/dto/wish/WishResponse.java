package kg.giftlist.giftlist.dto.wish;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishResponse {

    private UserWishResponse user;
    private WishCardResponse wish;
    private UserWishResponse bookedUser;

}
