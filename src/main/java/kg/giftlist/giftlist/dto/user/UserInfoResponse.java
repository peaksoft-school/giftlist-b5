package kg.giftlist.giftlist.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import kg.giftlist.giftlist.enums.ClothingSize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfoResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private String city;
    @JsonFormat(pattern="dd.MM.yyyy")
    @ApiModelProperty(dataType = "java.sql.Date")
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
