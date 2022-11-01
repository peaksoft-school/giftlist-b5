package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kg.giftlist.giftlist.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDate;

import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gifts")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gift_gen")
    @SequenceGenerator(name = "gift_gen", sequenceName = "gift_seq", initialValue = 7, allocationSize = 1)
    private Long id;

    private String name;

    private String photo;

    @Column(length = 4000)
    private String description;

    private Boolean isBlock = false;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = ALL)
    private Booking booking;

    @OneToOne
    private Category category;

    @OneToOne
    private SubCategory subCategory;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private User fromUser;

    @OneToMany(cascade = ALL, mappedBy = "gift")
    @JsonIgnore
    private List<Complaint> complaints;

}
