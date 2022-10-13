package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_gen")
    @SequenceGenerator(name = "complaint_gen", sequenceName = "complaint_seq", allocationSize = 1)
    private Long id;

    @Column(length = 4000)
    private String text;

    @OneToOne
    @JsonIgnore
    private User fromUser;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    @JsonIgnore
    private Wish wishes;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    @JsonIgnore
    private Gift gift;

}
