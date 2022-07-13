package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "holidays")
@NoArgsConstructor
@Getter
@Setter
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "holiday_gen",sequenceName = "holiday_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String photo;

    private Date holidayDate;

    private Boolean block;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Wish> wishes;

}
