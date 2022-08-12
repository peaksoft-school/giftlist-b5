package kg.giftlist.giftlist.dto.booking;

import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {

    private Long giftId;
}
