package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.enums.Status;

import java.util.List;

public interface GiftService {

    List<GiftResponse> findBy();
}
