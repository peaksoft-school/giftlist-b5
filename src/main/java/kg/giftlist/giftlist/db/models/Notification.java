package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.giftlist.giftlist.enums.NotificationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_gen")
    @SequenceGenerator(name = "notification_gen", sequenceName = "notification_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    private LocalDate createdAt;

    private boolean isRead;

    private Long recipientId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
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

    @OneToOne
    private Complaint complaintGift;

    @OneToOne
    private Complaint complaintWish;

}
