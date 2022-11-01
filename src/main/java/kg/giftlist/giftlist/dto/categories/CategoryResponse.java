package kg.giftlist.giftlist.dto.categories;

import kg.giftlist.giftlist.db.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
