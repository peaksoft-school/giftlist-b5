package kg.giftlist.giftlist.dto.mapper.complaint;

import kg.giftlist.giftlist.dto.wish.UserWishResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ComplaintResponse {

    private Long complaintId;
    private String text;
    private UserWishResponse fromUser;

}
