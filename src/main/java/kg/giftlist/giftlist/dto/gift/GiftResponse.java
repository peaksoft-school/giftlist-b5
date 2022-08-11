package kg.giftlist.giftlist.dto.gift;

import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.models.Booking;
import kg.giftlist.giftlist.models.Category;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.SubCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class GiftResponse {

    private UserGiftResponse user;
    private GiftCartResponse gift;


}
