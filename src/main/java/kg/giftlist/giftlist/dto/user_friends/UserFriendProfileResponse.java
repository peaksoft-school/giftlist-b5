package kg.giftlist.giftlist.dto.user_friends;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFriendProfileResponse {

    private Long userId;
    private String photo;
    private String firstName;
    private String lastName;
    private Boolean isFriend;
    private int wishCount;
    private int holidayCount;
}
