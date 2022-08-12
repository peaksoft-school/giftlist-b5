package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.models.Gift;

import java.util.List;

public interface GiftService {

    GiftResponse create(GiftRequest request);

    GiftResponse update(Long giftId, GiftRequest request);

    Gift findById(Long id);

    SimpleResponse deleteById(Long id);

    List<GiftResponse> getAll();
}
