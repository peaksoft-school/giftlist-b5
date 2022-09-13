package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Entity
@Table(name = "gifts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gift_gen")
    @SequenceGenerator(name = "gift_gen",sequenceName = "gift_seq", initialValue = 7, allocationSize = 1)
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

    @ManyToOne(cascade = {MERGE, REFRESH,DETACH})
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private User fromUser;

    @OneToMany(cascade = ALL, mappedBy = "gift")
    @JsonIgnore
    private List<Complaint> complaints;

    public Gift(GiftRequest request) {
        this.name= getName();
        this.photo = getPhoto();
        this.status = getStatus();
        this.category = getCategory();
        this.createdAt = getCreatedAt();
        this.description = getDescription();
    }
}
