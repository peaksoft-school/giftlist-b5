package kg.giftlist.giftlist.models;

import kg.giftlist.giftlist.enums.TypeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "types")
@NoArgsConstructor
@Getter
@Setter
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "type_gen", sequenceName = "type_seq", allocationSize = 1)
    private Long id;

    private Wish wish;

    private User user;

    private Gift gift;

    private Holiday holiday;

    private TypeStatus typeStatus;

}
