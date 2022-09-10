package kg.giftlist.giftlist.dto.mapper.complaint;

import kg.giftlist.giftlist.db.models.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComplaintViewMapper {

    public ComplaintResponse viewComplaints(Complaint complaint) {
        if (complaint == null) {
            return null;
        }
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setId(complaint.getId());
        complaintResponse.setText(complaint.getText());
        if (complaint.getWishes() != null) {
            complaintResponse.setUserName(complaint.getWishes().getUser().getFirstName());
            complaintResponse.setUserLastName(complaint.getWishes().getUser().getLastName());
            complaintResponse.setUserPhoto(complaint.getWishes().getUser().getPhoto());
            complaintResponse.setUserWish(complaint.getWishes());
        } else if (complaint.getGift() != null) {
            complaintResponse.setUserName(complaint.getGift().getUser().getFirstName());
            complaintResponse.setUserLastName(complaint.getGift().getUser().getLastName());
            complaintResponse.setUserPhoto(complaint.getGift().getUser().getPhoto());
            complaintResponse.setUserGift(complaint.getGift());
        } else if (complaint.getHoliday() != null) {
            complaintResponse.setUserName(complaint.getHoliday().getUser().getFirstName());
            complaintResponse.setUserLastName(complaint.getHoliday().getUser().getLastName());
            complaintResponse.setUserPhoto(complaint.getHoliday().getUser().getPhoto());
            complaintResponse.setUserHoliday(complaint.getHoliday());
            complaintResponse.setFromUserName(complaint.getFromUser().getFirstName());
            complaintResponse.setFromUserLastName(complaint.getFromUser().getLastName());
            complaintResponse.setFromUserPhoto(complaint.getFromUser().getPhoto());}
            return complaintResponse;
        }
    }

