package kg.giftlist.giftlist.dto.wish;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishResponse {

    private UserWishResponse ownerUser;
    private WishCardResponse wish;
    private UserWishResponse bookedUser;

}
