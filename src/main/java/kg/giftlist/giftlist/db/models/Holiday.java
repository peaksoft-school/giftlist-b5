package kg.giftlist.giftlist.db.models;

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
    @SequenceGenerator(name = "holiday_gen",sequenceName = "holiday_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String photo;

    private LocalDate holidayDate;

    private Boolean isBlock;

    @ManyToOne(cascade = {MERGE, REFRESH,DETACH})
    private User user;

    @OneToMany(cascade = ALL, mappedBy = "holidays")
    private List<Wish> wishes;
}
