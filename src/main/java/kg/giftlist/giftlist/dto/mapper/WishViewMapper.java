package kg.giftlist.giftlist.dto.mapper;

import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.models.Wish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WishViewMapper {

    public WishResponse viewWish(Wish wish) {

        if (wish == null) {

            return null;

        }

        return WishResponse.builder()

                .giftName(wish.getGiftName())

                .giftLink(wish.getGiftLink())

                .description(wish.getDescription())

                .photo(wish.getGiftPhoto())

                .wishDate(wish.getWishDate())

                .build();
    }

    public List<WishResponse> view(List<Wish> wishes){

        List<WishResponse> responses = new ArrayList<>();

        for (Wish wish: wishes) {

            responses.add(viewWish(wish));

        }

        return responses;

    }
}
