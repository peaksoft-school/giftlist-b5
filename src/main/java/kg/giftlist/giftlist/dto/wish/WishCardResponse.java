package kg.giftlist.giftlist.dto.wish;

import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Holiday;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WishCardResponse {

    private Long wishId;
    private String photo;
    private String wishName;
    private String wishLink;
    private String description;
    private LocalDate wishDate;
    private Holiday holiday;
    private Booking booking;
}
