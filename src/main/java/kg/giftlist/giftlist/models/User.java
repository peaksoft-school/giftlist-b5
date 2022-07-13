package kg.giftlist.giftlist.models;

import kg.giftlist.giftlist.enums.FriendStatus;
import kg.giftlist.giftlist.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen",sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private Boolean block;

    private FriendStatus friendStatus;

    @OneToMany
    private List<User> friends = new ArrayList<>();

    @OneToMany
    private List<Wish> wishes = new ArrayList<>();

    @OneToMany
    private List<Holiday> holidays = new ArrayList<>();

    @OneToMany
    private List<Gift> gifts = new ArrayList<>();

    @OneToOne
    private Booking booking;

    @OneToOne
    private UserInfo userInfo;
}
