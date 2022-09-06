package kg.giftlist.giftlist.dto.holiday;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class HolidayResponse {

    private Long id;
    private String name;
    private String photo;
    @JsonFormat(pattern="dd.MM.yyyy")
    @ApiModelProperty(dataType = "java.sql.Date")
    private LocalDate holidayDate;
}
