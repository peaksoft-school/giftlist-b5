package kg.giftlist.giftlist.dto.user_friends;
import kg.giftlist.giftlist.models.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFriendProfileResponse {
    private Long userId;
    private String photo;
    private String firstName;
    private String lastName;
    private int wishCount;
    private int holidayCount;
}
