package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;

import java.util.List;

public interface GiftService {
    GiftResponse create(GiftRequest request);
    GiftResponse update(Long id, GiftRequest request);

    GiftResponse findById(Long id);

    GiftResponse deleteById( Long id);
    List<GiftResponse> getAll();
}
