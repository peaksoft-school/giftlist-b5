package kg.giftlist.giftlist.dto.gift.mapper;

import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.models.Gift;
import org.springframework.stereotype.Component;

@Component
public class GiftEditMapper {


    public Gift  create(GiftRequest request){
        if (request == null){
            return null;
        }
        Gift gift = new Gift();
        gift.setName(request.getName());
        gift.setPhoto(request.getPhoto());
        gift.setStatus(request.getStatus());
        gift.setDescription(request.getDescription());
        return gift;
    }

    public void update(Gift gift1,GiftRequest request2){
      gift1.setName(request2.getName());
      gift1.setPhoto(request2.getPhoto());
      gift1.setStatus(request2.getStatus());
      gift1.setDescription(request2.getDescription());
    }
}
