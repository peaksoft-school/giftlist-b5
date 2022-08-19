package kg.giftlist.giftlist.dto.mapper.complaint;

import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@RequiredArgsConstructor
public class ComplaintResponse {
    private Long id;

    private String userPhoto;
    private String userName;
    private String userLastName;

    private Wish userWish;
    private Gift userGift;

    private String text;

    private String fromUserPhoto;
    private String fromUserLastName;
    private String fromUserName;
}
