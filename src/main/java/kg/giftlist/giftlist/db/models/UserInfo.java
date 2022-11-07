package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.enums.ClothingSize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "usersInfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userInfo_gen")
    @SequenceGenerator(name = "userInfo_gen", sequenceName = "userInfo_seq", initialValue = 7, allocationSize = 1)
    private Long id;

    private String city;

    @Past
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ClothingSize clothingSize;

    @Min(35)
    @Max(44)
    private int shoeSize;

    @Column(length = 4000)
    private String hobby;

    @Column(length = 4000)
    private String importantNote;

    private String instagramLink;

    private String telegramLink;

    private String facebookLink;

    private String vkLink;

    @JsonIgnore
    @OneToOne(mappedBy = "userInfo")
    private User user;

    public UserInfo(UserInfoRequest userInfoRequest) {
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
