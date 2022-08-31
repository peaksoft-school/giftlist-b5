package kg.giftlist.giftlist.dto.wish;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class WishRequest {
    private String photo;
    private String wishName;
    private String wishLink;
    private String description;
    private Long holidayId;
    private LocalDate wishDate;
}
