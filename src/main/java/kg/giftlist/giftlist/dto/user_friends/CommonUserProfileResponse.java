package kg.giftlist.giftlist.dto.user_friends;

import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.models.UserInfo;
import kg.giftlist.giftlist.models.Wish;
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
    private UserInfo userInfo;
    private List<Wish> wishes;
    private List<Holiday> holidays;
    private List<Gift> gifts;
}
