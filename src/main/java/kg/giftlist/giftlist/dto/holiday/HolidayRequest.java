package kg.giftlist.giftlist.dto.holiday;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HolidayRequest {

    String photo;
    String name;
    LocalDate date;

}
