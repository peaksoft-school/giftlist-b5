package kg.giftlist.giftlist.models;

import kg.giftlist.giftlist.enums.FriendStatus;
import kg.giftlist.giftlist.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen",sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isBlock;

    @Enumerated(EnumType.STRING)
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
}
