package kg.giftlist.giftlist.dto.mapper.complaint;

import kg.giftlist.giftlist.dto.gift.UserGiftResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ComplaintGiftResponse {

    private Long complaintId;
    private String text;
    private UserGiftResponse fromUser;

}
