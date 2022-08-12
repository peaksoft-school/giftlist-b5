package kg.giftlist.giftlist.dto.gift.mapper;

import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.models.Category;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.SubCategory;
import kg.giftlist.giftlist.repositories.CategoryRepository;
import kg.giftlist.giftlist.repositories.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiftEditMapper {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    public Gift  create(GiftRequest request){
        if (request == null){
            return null;
        }
        Gift gift = new Gift();
        gift.setName(request.getName());
        gift.setPhoto(request.getPhoto());
        gift.setStatus(request.getStatus());;
        gift.setDescription(request.getDescription());
        return gift;
    }

    public void update(Gift gift, GiftRequest request){
        gift.setName(request.getName());
        gift.setPhoto(request.getPhoto());
        gift.setStatus(request.getStatus());
        gift.setDescription(request.getDescription());
    }
}
