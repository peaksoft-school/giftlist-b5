package kg.giftlist.giftlist.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "complaints")
@NoArgsConstructor
@Getter
@Setter
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_gen")
    @SequenceGenerator(name = "complaint_gen", sequenceName = "complaint_seq", allocationSize = 1)
    private Long id;

    private String text;

    @OneToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

//    @OneToOne
//    private User user;

    @OneToOne
    @JoinColumn(name = "wish_id")

    private Wish wish;

    @OneToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;
}
