package kg.giftlist.giftlist.dto.gift;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftResponse {

    private UserGiftResponse ownerUser;
    private GiftCartResponse gift;
    private UserGiftResponse bookedUser;

}
