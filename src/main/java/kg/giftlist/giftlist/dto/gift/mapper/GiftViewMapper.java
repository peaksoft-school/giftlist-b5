package kg.giftlist.giftlist.dto.gift.mapper;
import kg.giftlist.giftlist.db.models.Complaint;
import kg.giftlist.giftlist.dto.booking.BookingResponse;
import kg.giftlist.giftlist.dto.gift.GiftCartResponse;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.gift.UserGiftResponse;
import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintGiftResponse;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class GiftViewMapper {

    public GiftResponse viewCommonGiftCard(User user, Gift gift) {
        if (gift == null) {
            return null;
        }
        GiftResponse response = new GiftResponse();
        response.setOwnerUser(viewUserGift(user));
        response.setGift(viewGiftCard(gift));
        if (gift.getBooking()==null || gift.getBooking().getUser()==null) {
            return response;
        }
        response.setBookedUser(viewUserGift(gift.getBooking().getUser()));
        return response;
    }

    public List<GiftResponse> getAllGifts(List<Gift> gifts) {
        List<GiftResponse> giftResponses = new ArrayList<>();
        for (Gift gift : gifts) {
            User user = gift.getUser();
            if(!gift.getIsBlock()) {
                giftResponses.add(viewCommonGiftCard(user, gift));
            }
        }
        return giftResponses;
    }

    public UserGiftResponse viewUserGift(User user){
        if (user == null) {
            return null;
        }
        UserGiftResponse userGiftResponse = new UserGiftResponse();
        userGiftResponse.setUserId(user.getId());
        userGiftResponse.setFirstName(user.getFirstName());
        userGiftResponse.setLastName(user.getLastName());
        userGiftResponse.setPhoto(user.getPhoto());
        if (user.getUserInfo()==null || user.getUserInfo().getPhoneNumber()==null){
            return userGiftResponse;
        }
        userGiftResponse.setPhoneNumber(user.getUserInfo().getPhoneNumber());
        return userGiftResponse;
    }

    public GiftCartResponse viewGiftCard(Gift gift){
        if (gift == null && !gift.getIsBlock()) {
            return null;
        }
        GiftCartResponse giftCartResponse = new GiftCartResponse();
        giftCartResponse.setGiftId(gift.getId());
        giftCartResponse.setName(gift.getName());
        giftCartResponse.setPhoto(gift.getPhoto());
        giftCartResponse.setCreatedAt(gift.getCreatedAt());
        giftCartResponse.setStatus(gift.getStatus());
        giftCartResponse.setDescription(gift.getDescription());
        giftCartResponse.setCategory(gift.getCategory());
        giftCartResponse.setSubCategory(gift.getSubCategory());
        giftCartResponse.setComplaints(viewAllComplaints(gift.getComplaints()));
        giftCartResponse.setIsBlock(gift.getIsBlock());
        giftCartResponse.setBooking(gift.getBooking());
        return giftCartResponse;
    }

    public ComplaintGiftResponse viewComplaints(Complaint complaint) {
        if (complaint == null) {
            return null;
        }
        ComplaintGiftResponse complaintGiftResponse = new ComplaintGiftResponse();
        complaintGiftResponse.setComplaintId(complaint.getId());
        complaintGiftResponse.setText(complaint.getText());
        complaintGiftResponse.setFromUser(viewUserGift(complaint.getFromUser()));
        return complaintGiftResponse;
    }

    public List<ComplaintGiftResponse> viewAllComplaints(List<Complaint> complaints) {
        List<ComplaintGiftResponse> complaintResponses = new ArrayList<>();
        for (Complaint complaint : complaints) {
            complaintResponses.add(viewComplaints(complaint));
        }
        return complaintResponses;
    }

}