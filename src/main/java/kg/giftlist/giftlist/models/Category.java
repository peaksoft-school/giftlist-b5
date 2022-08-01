package kg.giftlist.giftlist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name = "category_gen",sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SubCategory> subCategories;
}
