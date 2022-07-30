package kg.giftlist.giftlist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "mailingLists")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Component
public class MailingList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mailingList_gen", sequenceName = "mailingList_seq", allocationSize = 1)
    private Long id;

    private String email;
}
