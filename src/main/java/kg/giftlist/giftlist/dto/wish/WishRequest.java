package kg.giftlist.giftlist.dto.wish;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class WishRequest {

    private String photo;

    private String giftName;

    private String giftLink;

    private String description;

    private LocalDate wishDate;

}
