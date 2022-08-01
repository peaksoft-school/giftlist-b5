package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "requestToFriends")
@NoArgsConstructor
@Getter
@Setter
public class RequestToFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestToFriends_gen")
    @SequenceGenerator(name = "requestToFriends_gen", sequenceName = "requestToFriends_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private User from;

    @OneToOne
    private User to;

}
