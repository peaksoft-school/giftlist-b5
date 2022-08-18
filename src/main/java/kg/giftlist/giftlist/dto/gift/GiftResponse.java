package kg.giftlist.giftlist.dto.gift;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftResponse {

    private UserGiftWishResponse user;
    private GiftCartResponse gift;
    private UserGiftWishResponse bookedUser;

}
