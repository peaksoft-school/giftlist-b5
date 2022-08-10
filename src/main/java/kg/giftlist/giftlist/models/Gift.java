package kg.giftlist.giftlist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.giftlist.giftlist.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Entity
@Table(name = "gifts")
@NoArgsConstructor
@Getter
@Setter
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gift_gen")
    @SequenceGenerator(name = "gift_gen",sequenceName = "gift_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String photo;

    private String description;

    private Boolean isBlock;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = ALL)
    @JsonIgnore
    private Booking booking;

    @OneToOne
    private Category category;

    @ManyToOne(cascade = {MERGE, REFRESH,DETACH})
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private User fromUser;

}
