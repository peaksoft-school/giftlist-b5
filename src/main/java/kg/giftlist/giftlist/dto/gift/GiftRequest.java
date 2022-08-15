package kg.giftlist.giftlist.dto.gift;
import kg.giftlist.giftlist.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftRequest {

    private String name;
    private String photo;
    private Long categoryId;
    private Long subCategoryId;
    private Status status;
    private String description;
}
