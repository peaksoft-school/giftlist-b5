package kg.giftlist.giftlist.dto.gift.mapper;

import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.models.Gift;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GiftViewMapper {

    public GiftResponse viewGift(Gift gift) {
        if (gift == null) {
            return null;
        }

        GiftResponse response = new GiftResponse();
        response.setId(gift.getId());
        response.setName(gift.getName());
        response.setPhoto(gift.getPhoto());
        response.setStatus(gift.getStatus());
        response.setDate(gift.getDate());
        response.setBooking(gift.getBooking());

        return response;
    }
    public List<GiftResponse> view(List<Gift> gifts){
        List<GiftResponse> responses = new ArrayList<>();
        for (Gift gift: gifts) {
            responses.add(viewGift(gift));
        }
        return responses;
    }
}