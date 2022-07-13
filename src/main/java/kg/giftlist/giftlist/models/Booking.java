package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "booking_gen",sequenceName = "booking_seq", allocationSize = 1)
    private Long id;

    private User user;

    private List<Wish> wishes = new ArrayList<>();


    private List<Gift> gifts = new ArrayList<>();
}
