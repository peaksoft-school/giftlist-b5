package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mailingLists")
@NoArgsConstructor
@Getter
@Setter
public class MailingList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mailingList_gen", sequenceName = "mailingList_seq", allocationSize = 1)
    private Long id;

    private String text;

    private String email;
}
