package kg.giftlist.giftlist.dto.user;

import kg.giftlist.giftlist.models.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResponse {

    private Long userId;

    private String photo;

    private String firstName;

    private String lastName;

    private String email;

    private UserInfo userInfo;
}
