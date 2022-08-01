package kg.giftlist.giftlist.dto.gift;

import kg.giftlist.giftlist.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GiftResponse {

    private Long id;
    private String giftPhoto;
    private String giftName;
    private LocalDate giftDate;
    private Status status;
    private Long booking_user_id;
}
