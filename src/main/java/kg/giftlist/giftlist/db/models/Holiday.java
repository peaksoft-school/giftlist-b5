package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holiday_gen")
    @SequenceGenerator(name = "holiday_gen", sequenceName = "holiday_seq", initialValue = 8, allocationSize = 1)
    private Long id;

    private String name;

    private String photo;

    @JsonFormat(pattern = "dd.MM.yyyy")
//    @ApiModelProperty(dataType = "java.sql.Date")
    private LocalDate holidayDate;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    @JsonIgnore
    private User user;

    @OneToMany(cascade = ALL, mappedBy = "holiday")
    @JsonIgnore
    private List<Wish> wishes;

}
