package kg.giftlist.giftlist.dto.user_info;

import kg.giftlist.giftlist.enums.ClothingSize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
public class UserInfoRequest {

    private String photo;

    private String country;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ClothingSize clothingSize;

    private int shoeSize;

    private String hobby;

    private String importantNote;

    private String instagramLink;

    private String telegramLink;

    private String facebookLink;

    private String vkLink;

    private String first_name;
    private String last_name;
    private String email;
}
