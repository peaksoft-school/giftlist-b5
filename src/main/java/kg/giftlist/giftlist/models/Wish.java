package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    private LocalDateTime date;

    private String description;

    private Boolean block;

    private Boolean hidden;

    private User user;

    private User fromUser;

    private Holiday holidays;

}
