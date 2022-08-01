package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "wishes")
@NoArgsConstructor
@Getter
@Setter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_gen")
    @SequenceGenerator(name = "wish_gen",sequenceName = "wish_seq", allocationSize = 1)
    private Long id;

    private String giftName;

    private String giftLink;

    private String giftPhoto;

    private LocalDateTime createdAt;

    private LocalDate wishDate;

    private String description;

    private Boolean isBlock;

    private Boolean isHidden;

    @ManyToOne(cascade = ALL)
    private Booking booking;

    @ManyToOne(cascade = {MERGE, REFRESH,DETACH})
    private User user;

    @ManyToOne
    private User fromUser;

    @ManyToOne(cascade = {DETACH,REFRESH,MERGE})
    private Holiday holidays;

}
