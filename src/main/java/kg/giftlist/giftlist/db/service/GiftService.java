package kg.giftlist.giftlist.db.service;


import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.enums.Status;

import java.util.List;

public interface GiftService {

    GiftResponse create(GiftRequest request);

    GiftResponse update(Long giftId, GiftRequest request);

    Gift findById(Long id);

    SimpleResponse deleteById(Long id);

    List<GiftResponse> getAllOwnGifts();

    List<GiftResponse> filter(String search,Status status,Long categoryId,Long subCategoryId);
}
