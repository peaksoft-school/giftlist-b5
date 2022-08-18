package kg.giftlist.giftlist.dto.wish;

import kg.giftlist.giftlist.db.models.Booking;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WishCardResponse {

    private Long id;
    private String photo;
    private String giftName;
    private String giftLink;
    private String description;
    private LocalDate wishDate;
    private String holidayName;
    private Booking booking;
}
