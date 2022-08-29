package kg.giftlist.giftlist.db.models;
import kg.giftlist.giftlist.enums.FriendStatus;
import kg.giftlist.giftlist.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", initialValue = 6, allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isBlock;

    @Enumerated(EnumType.STRING)
    @Transient
    private FriendStatus friendStatus;

    @ManyToMany
    @JoinTable(name = "request_to_friends")
    private List<User> requestToFriends = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "friends")
    private List<User> friends = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Wish> wishes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    void addNotification(Notification newNotification){
        notifications.add(newNotification);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Holiday> holidays = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Gift> gifts = new ArrayList<>();

    @OneToOne(cascade = {ALL}, mappedBy = "user")
    private Booking booking;

    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;

    private String photo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String firstName, String email, String password, Role role) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void addRequestToFriend(User user) {
        if (requestToFriends==null) {
            requestToFriends = new ArrayList<>();
        }
        requestToFriends.add(user);
    }

    public void acceptToFriend(User user) {
        if (friends==null) {
            friends = new ArrayList<>();
        }
        friends.add(user);
    }
}
