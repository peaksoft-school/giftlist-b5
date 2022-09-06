package kg.giftlist.giftlist.dto.wish;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
    @JsonFormat(pattern="dd.MM.yyyy")
    @ApiModelProperty(dataType = "java.sql.Date")
    private LocalDate wishDate;
    private Holiday holiday;
    private Booking booking;
}
