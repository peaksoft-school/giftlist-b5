package kg.giftlist.giftlist.dto.wish;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Holiday;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import kg.giftlist.giftlist.enums.AddWishStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class WishCardResponse {

    private Long wishId;
    private String photo;
    private String wishName;
    private String wishLink;
    private String description;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate wishDate;

    private Boolean isBlock;
    private Holiday holiday;
    private AddWishStatus addWishStatus;
    private List<ComplaintResponse> complaints;
    private Booking booking;

}
