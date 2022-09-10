package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "wishes")
@NoArgsConstructor
@Getter
@Setter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_gen")
    @SequenceGenerator(name = "wish_gen",sequenceName = "wish_seq", initialValue = 9, allocationSize = 1)
    private Long id;

    private String wishName;

    private String wishLink;

    private String wishPhoto;

    private LocalDateTime createdAt;

    private LocalDate wishDate;

    @Column(length = 4000)
    private String description;

    private Boolean isBlock;

    private Boolean isHidden;

    @ManyToOne
    @JsonIgnore
    private Booking booking;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST} )
    @JsonIgnore
    private User user;

    public Long getUserId(){
       return user.getId();
    }

    @ManyToOne
    @JsonIgnore
    private User fromUser;

    @ManyToOne(targetEntity = Holiday.class, fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "holiday")
    @JsonIgnore
    private Holiday holiday;

    @OneToMany(cascade = ALL, mappedBy = "wishes")
    @JsonIgnore
    private List<Complaint> complaints;

}
