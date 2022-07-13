package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishes")
@NoArgsConstructor
@Getter
@Setter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "wish_gen",sequenceName = "wish_seq", allocationSize = 1)
    private Long id;

    private String giftName;

    private String giftLink;

    private String giftPhoto;

    private LocalDateTime createdAt;

    private LocalDate wishDate;

    private String description;

    private Boolean block;

    private Boolean isHidden;

    @ManyToOne
    private Booking booking;

    @ManyToOne
    private User user;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private Holiday holidays;

}
