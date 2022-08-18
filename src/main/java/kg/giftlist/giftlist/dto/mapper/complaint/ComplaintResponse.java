package kg.giftlist.giftlist.dto.mapper.complaint;

import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
public class ComplaintResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "holiday_gen",sequenceName = "holiday_seq", allocationSize = 1)
    private Long id;

    private String userPhoto;
    private String userName;
    private String userLastName;
    private WishResponse userWish;

    private String text;

    private String fromUserPhoto;
    private String fromUserLastName;
    private String fromUserName;
}
