package kg.giftlist.giftlist.dto.wish;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WishResponse {
    private Long id;
    private String photo;
    private String giftName;
    private String giftLink;
    private String description;
    private LocalDate wishDate;
    private String holidayName;
    private Long userId;
}
