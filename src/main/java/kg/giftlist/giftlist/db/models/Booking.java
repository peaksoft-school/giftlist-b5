package kg.giftlist.giftlist.db.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_gen")
    @SequenceGenerator(name = "booking_gen",sequenceName = "booking_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JsonIgnore
    private User user;
    @OneToMany(cascade = ALL)
    @JsonIgnore
    private List<Wish> wishes = new ArrayList<>();
    @OneToMany(cascade = ALL)
    @JsonIgnore
    private List<Gift> gifts = new ArrayList<>();
}
