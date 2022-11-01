package kg.giftlist.giftlist.dto.gift;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGiftResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String photo;
    private String phoneNumber;

}
