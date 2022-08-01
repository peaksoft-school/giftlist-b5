package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.models.User;

import java.util.List;

public interface WishService {

    WishResponse create(WishRequest request);

    WishResponse update(Long id, WishRequest wishRequest, User user);

    WishResponse findById(Long id);

    SimpleResponse deleteById(Long id);

    List<WishResponse> getAllWishes();

}
