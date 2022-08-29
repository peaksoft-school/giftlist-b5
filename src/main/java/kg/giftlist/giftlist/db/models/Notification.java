package kg.giftlist.giftlist.db.models;

import kg.giftlist.giftlist.enums.NotificationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "notifications")
@NoArgsConstructor
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_gen")
    @SequenceGenerator(name = "notification_gen", sequenceName = "notification_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    private LocalDate createdAt;

    private Boolean isRead;

    @ManyToOne
    private User user;

    @OneToOne
    private Wish wish;

    @OneToOne
    private Gift gift;

    @OneToOne
    private Holiday holiday;

    @OneToOne
    private Booking giftBooking;

    @OneToOne
    private Booking wishBooking;

    @OneToMany
    private List<Type> types;

}
