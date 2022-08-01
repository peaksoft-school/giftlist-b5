package kg.giftlist.giftlist.dto.user_info;

import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.enums.ClothingSize;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.models.Wish;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserInfoResponse {

    private Long id;

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
    //user
    private String first_name;

    private String last_name;

    private String email;

    private List<GiftResponse> gifts;

    private List<WishResponse> wishes;

    private List<HolidayResponse> holiday;
}
