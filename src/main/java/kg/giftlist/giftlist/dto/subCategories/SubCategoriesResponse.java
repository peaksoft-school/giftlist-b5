package kg.giftlist.giftlist.dto.subCategories;

import kg.giftlist.giftlist.db.models.SubCategory;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SubCategoriesResponse {

    private Long id;
    private String  name;

    public SubCategoriesResponse(){

    }

    public SubCategoriesResponse(SubCategory subCategory){
        this.id = subCategory.getId();
        this.name = subCategory.getName();
    }
}
