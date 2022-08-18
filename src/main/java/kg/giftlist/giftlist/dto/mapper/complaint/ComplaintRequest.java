package kg.giftlist.giftlist.dto.mapper.complaint;


import kg.giftlist.giftlist.db.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {
    private String text;

    private User user;
}
