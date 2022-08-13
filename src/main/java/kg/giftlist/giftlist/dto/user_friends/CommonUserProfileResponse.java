package kg.giftlist.giftlist.dto.user_friends;
import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.Holiday;
import kg.giftlist.giftlist.db.models.UserInfo;
import kg.giftlist.giftlist.db.models.Wish;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class CommonUserProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private Boolean isFriend;
    private Boolean isRequestToFriend;
    private UserInfo userInfo;
    private List<Wish> wishes;
    private List<Holiday> holidays;
    private List<Gift> gifts;
}
