package kg.giftlist.giftlist.dto.wish;

import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.models.Wish;
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
}
