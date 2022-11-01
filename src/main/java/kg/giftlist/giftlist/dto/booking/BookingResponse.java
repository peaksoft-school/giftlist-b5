package kg.giftlist.giftlist.dto.booking;

import kg.giftlist.giftlist.dto.gift.UserGiftResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {

    private UserGiftResponse userBooked;

}
