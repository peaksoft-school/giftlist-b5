package kg.giftlist.giftlist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subCategories")
@NoArgsConstructor
@Getter
@Setter
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategory_gen")
    @SequenceGenerator(name = "subCategory_gen",sequenceName = "subCategory_seq", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne
    private Category category;

}
