package kg.giftlist.giftlist.dto.user_friends;

import kg.giftlist.giftlist.db.models.UserInfo;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.enums.FriendStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CommonUserProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private UserInfo userInfo;
    private List<WishResponse> wishes;
    private List<HolidayResponse> holidays;
    private List<GiftResponse> gifts;
    private FriendStatus friendStatus;
    private Boolean isBlock;
}
