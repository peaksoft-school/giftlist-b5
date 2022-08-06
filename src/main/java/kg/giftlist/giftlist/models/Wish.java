package kg.giftlist.giftlist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    private Boolean isBlock;

    private Boolean isHidden;

    @ManyToOne
    private Booking booking;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST} )
    @JoinColumn(name = "users")
    @NotNull(message = "User not set")
    @JsonIgnore
    private User user;
   public Long getUserId(){
       return user.getId();
   }

    @ManyToOne
    private User fromUser;

    @ManyToOne(targetEntity = Holiday.class, fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "holidays")
    @JsonIgnore
    private Holiday holidays;

    public String getHolidayName(){
        return holidays.getName();
    }




}
