package kg.giftlist.giftlist.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "send_mailing_list")
@NoArgsConstructor
@Getter
@Setter
public class SendMailingList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sendMailingList_gen")
    @SequenceGenerator(name = "sendMailingList_gen", sequenceName = "requestToFriends_seq", allocationSize = 1)
    private Long id;

    private String photo;

    private String title;

    private String text;

    private LocalDateTime createdAt;

    @OneToMany
    private List<MailingList> mailingLists;

}
