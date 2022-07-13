package kg.giftlist.giftlist.models;

import kg.giftlist.giftlist.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "gifts")
@NoArgsConstructor
@Getter
@Setter
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "gift_gen",sequenceName = "gift_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String photo;

    private String description;

    private Boolean isBlock;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private Booking booking;

    @OneToOne
    private Country country;

    @OneToOne
    private Category category;

    @ManyToOne
    private User user;

    @ManyToOne
    private User fromUser;

}
