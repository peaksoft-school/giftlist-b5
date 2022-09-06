package kg.giftlist.giftlist.dto.gift;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import kg.giftlist.giftlist.db.models.SubCategory;
import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Category;
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
    @JsonFormat(pattern="dd.MM.yyyy")
    @ApiModelProperty(dataType = "java.sql.Date")
    private LocalDate createdAt;
    private String description;
    private Category category;
    private SubCategory subCategory;
    private Booking booking;
}
