package kg.giftlist.giftlist.db.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subCategories")
@NoArgsConstructor
@Getter
@Setter
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategory_gen")
    @SequenceGenerator(name = "subCategory_gen", sequenceName = "subCategory_seq", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnore
    private Category category;

}
