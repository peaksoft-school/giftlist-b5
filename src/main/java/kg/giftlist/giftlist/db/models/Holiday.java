package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "holidays")
@NoArgsConstructor
@Getter
@Setter
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "holiday_gen")
    @SequenceGenerator(name = "holiday_gen",sequenceName = "holiday_seq", initialValue = 8, allocationSize = 1)
    private Long id;

    private String name;

    private String photo;

    @JsonFormat(pattern="dd.MM.yyyy")
    @ApiModelProperty(dataType = "java.sql.Date")
    private LocalDate holidayDate;

    private Boolean isBlock;

    @ManyToOne(cascade = {MERGE, REFRESH,DETACH})
    @JsonIgnore
    private User user;

    @OneToMany(cascade = ALL, mappedBy = "holiday")
    @JsonIgnore
    private List<Wish> wishes;
}
