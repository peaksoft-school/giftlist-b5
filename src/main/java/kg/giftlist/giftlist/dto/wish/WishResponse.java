package kg.giftlist.giftlist.dto.wish;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.giftlist.giftlist.models.Holiday;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
