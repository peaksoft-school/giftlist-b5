package kg.giftlist.giftlist.dto.wish;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWishResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String photo;
    private String phoneNumber;

}
