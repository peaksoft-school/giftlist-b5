package kg.giftlist.giftlist.dto.mapper.wish;

import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Complaint;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.dto.booking.BookingResponse;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.db.models.Wish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WishViewMapper {

    public WishResponse viewWish(Wish wish) {

        if (wish == null) {
            return null;
        }
        return WishResponse.builder()
                .id(wish.getId())
                .giftName(wish.getGiftName())
                .giftLink(wish.getGiftLink())
                .description(wish.getDescription())
                .photo(wish.getGiftPhoto())
                .wishDate(wish.getWishDate())
                .holidayName(wish.getHolidayName())
                .userId(wish.getUserId())
                .build();
    }

    public List<WishResponse> view(List<Wish> wishes){

        List<WishResponse> responses = new ArrayList<>();
        for (Wish wish: wishes) {
            responses.add(viewWish(wish));
        }
        return responses;
    }


    public ComplaintResponse viewComplaint(Complaint complaint, User user) {
        if (complaint==null){
            return null;
        }
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setFromUserName(user.getFirstName());
        complaintResponse.setFromUserLastName(user.getLastName());
        complaintResponse.setFromUserPhoto(user.getPhoto());
        return complaintResponse;
    }
}
