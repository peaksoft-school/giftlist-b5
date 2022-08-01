package kg.giftlist.giftlist.dto.wish;

import kg.giftlist.giftlist.models.Holiday;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WishResponse {

    private Long id;
    private String photo;
    private String wishName;
    private LocalDate localDate;
    private Boolean isBlock;
    private String holidayName;
}
