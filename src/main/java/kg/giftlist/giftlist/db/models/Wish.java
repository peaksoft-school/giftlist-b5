package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "wishes")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_gen")
    @SequenceGenerator(name = "wish_gen", sequenceName = "wish_seq", initialValue = 9, allocationSize = 1)
    private Long id;

    private String wishName;

    private String wishLink;

    private String wishPhoto;

    private LocalDateTime createdAt;

    private LocalDate wishDate;

    @Column(length = 4000)
    private String description;

    private Boolean isBlock = false;

    private Boolean isHidden;

    @ManyToOne
    @JsonIgnore
    private Booking booking;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private User fromUser;

    @ManyToOne(targetEntity = Holiday.class, fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "holiday")
    @JsonIgnore
    private Holiday holiday;

    @OneToMany(cascade = ALL, mappedBy = "wishes")
    @JsonIgnore
    private List<Complaint> complaints;

    public Long getUserId() {
        return user.getId();
    }

}
