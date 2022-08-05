package kg.giftlist.giftlist.models;

import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.enums.ClothingSize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usersInfo")
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userInfo_gen")
    @SequenceGenerator(name = "userInfo_gen",sequenceName = "userInfo_seq", allocationSize = 1)
    private Long id;

    private String photo;

    private String city;

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

    @OneToOne(mappedBy = "userInfo")
    private User user;

    public UserInfo(UserInfoRequest userInfoRequest) {
        this.photo = userInfoRequest.getPhoto();
        this.city = userInfoRequest.getCity();
        this.dateOfBirth = userInfoRequest.getDateOfBirth();
        this.phoneNumber = userInfoRequest.getPhoneNumber();
        this.clothingSize = userInfoRequest.getClothingSize();
        this.shoeSize = userInfoRequest.getShoeSize();
        this.hobby = userInfoRequest.getHobby();
        this.importantNote = userInfoRequest.getImportantNote();
        this.instagramLink = userInfoRequest.getInstagramLink();
        this.telegramLink = userInfoRequest.getTelegramLink();
        this.facebookLink = userInfoRequest.getFacebookLink();
        this.vkLink = userInfoRequest.getVkLink();
    }
}
