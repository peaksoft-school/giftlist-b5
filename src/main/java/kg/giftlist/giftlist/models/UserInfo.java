package kg.giftlist.giftlist.models;

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

    @OneToOne
    private User user;

}
