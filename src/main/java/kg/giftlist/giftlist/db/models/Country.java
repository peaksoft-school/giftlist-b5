package kg.giftlist.giftlist.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_gen")
    @SequenceGenerator(name = "country_gen",sequenceName = "country_seq", allocationSize = 1)
    private Long id;

    private String name;

}
