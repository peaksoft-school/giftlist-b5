package kg.giftlist.giftlist.dto.wish;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Holiday;
import kg.giftlist.giftlist.enums.AddWishStatus;
import kg.giftlist.giftlist.enums.ComplaintStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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
    private AddWishStatus addWishStatus;
    private ComplaintStatus complaintStatus;
}
