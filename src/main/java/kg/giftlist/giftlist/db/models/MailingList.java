package kg.giftlist.giftlist.db.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mailingLists")
public class MailingList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mailingList_gen")
    @SequenceGenerator(name = "mailingList_gen", sequenceName = "mailingList_seq", allocationSize = 1)
    private Long id;

    private String email;

}
