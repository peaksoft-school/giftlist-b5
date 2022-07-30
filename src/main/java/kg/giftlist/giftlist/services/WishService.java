package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.models.Wish;

import java.util.List;

public interface WishService {

    List<Wish> searchWithGiftName(String keyword);
}
