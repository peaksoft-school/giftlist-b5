package kg.giftlist.giftlist.dto.user;

import kg.giftlist.giftlist.enums.ClothingSize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfoRequest {

    private String photo;

    private String country;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private ClothingSize clothingSize;

    private int shoeSize;

    private String hobby;

    private String importantNote;

    private String instagramLink;

    private String telegramLink;

    private String facebookLink;

    private String vkLink;
}
