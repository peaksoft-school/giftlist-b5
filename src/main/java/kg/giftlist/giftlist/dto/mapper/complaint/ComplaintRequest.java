package kg.giftlist.giftlist.dto.mapper.complaint;


import kg.giftlist.giftlist.db.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ComplaintRequest {
    private String text;

    private User user;
}
