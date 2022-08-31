package kg.giftlist.giftlist.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.CascadeType.*;

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

    @Column(length = 4000)
    private String text;

    @OneToOne
    private User fromUser;

    @ManyToOne(cascade = {MERGE, REFRESH,DETACH})
    private Wish wishes;

    @ManyToOne(cascade = {MERGE, REFRESH,DETACH})
    private Gift gift;
}
