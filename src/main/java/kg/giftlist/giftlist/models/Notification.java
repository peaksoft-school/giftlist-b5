package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "notifications")
@NoArgsConstructor
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "notification_gen", sequenceName = "notification_seq", allocationSize = 1)
    private Long id;

    private User user;

    private String text;

    private Date createdAt;

    private Boolean read;

    private List<Type> types;

}