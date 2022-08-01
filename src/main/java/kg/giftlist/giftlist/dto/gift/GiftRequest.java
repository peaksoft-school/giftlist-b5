package kg.giftlist.giftlist.dto.gift;

import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.models.Booking;
import kg.giftlist.giftlist.models.Category;
import kg.giftlist.giftlist.models.Country;
import kg.giftlist.giftlist.models.SubCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GiftRequest {
    private String name;
    private String photo;
    private Category category;
    private Status status;
    private SubCategory subCategory;
    private String description;
}
