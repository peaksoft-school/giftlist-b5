package kg.giftlist.giftlist.db.models;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_gen")
    @SequenceGenerator(name = "type_gen", sequenceName = "type_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeStatus typeStatus;

    @OneToOne
    private User user;

    @OneToOne
    private Wish wish;

    @OneToOne
    private Gift gift;

    @OneToOne
    private Holiday holiday;

}
