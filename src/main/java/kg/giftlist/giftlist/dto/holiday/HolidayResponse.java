package kg.giftlist.giftlist.dto.holiday;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class HolidayResponse {

    private Long id;
    private String name;
    private String photo;
    private LocalDate holidayDate;
}
