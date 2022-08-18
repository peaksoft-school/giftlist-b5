package kg.giftlist.giftlist.dto.wish;

import kg.giftlist.giftlist.dto.gift.UserGiftWishResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishResponse {

    private UserGiftWishResponse user;
    private WishCardResponse wish;
    private UserGiftWishResponse bookedUser;

}
