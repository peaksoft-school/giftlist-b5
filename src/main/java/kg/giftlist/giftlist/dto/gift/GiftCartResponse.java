package kg.giftlist.giftlist.dto.gift;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.models.Booking;
import kg.giftlist.giftlist.models.Category;
import kg.giftlist.giftlist.models.SubCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GiftCartResponse {

    private Long giftId;
    private String name;
    private String photo;
    private Status status;
    private LocalDate createdAt;
    private String description;
    private Category category;
    private Booking booking;


}
