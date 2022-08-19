package kg.giftlist.giftlist.dto.mapper.complaint;

import kg.giftlist.giftlist.db.models.Complaint;
import kg.giftlist.giftlist.db.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComplaintViewMapper {

    public ComplaintResponse viewComplaintWish(Complaint complaint, User user) {
        if (complaint==null){
            return null;
        }
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setId(complaint.getId());
        complaintResponse.setText(complaint.getText());
        if (complaint.getWish()!=null){
            complaintResponse.setUserName(complaint.getWish().getUser().getFirstName());
            complaintResponse.setUserLastName(complaint.getWish().getUser().getLastName());
            complaintResponse.setUserPhoto(complaint.getWish().getUser().getPhoto());
            complaintResponse.setUserWish(complaint.getWish());
        }

        complaintResponse.setFromUserName(user.getFirstName());
        complaintResponse.setFromUserLastName(user.getLastName());
        complaintResponse.setFromUserPhoto(user.getPhoto());
        return complaintResponse;
    }
    public ComplaintResponse viewComplaintGift(Complaint complaint, User user) {
        if (complaint == null) {
            return null;
        }
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setId(complaint.getId());
        complaintResponse.setText(complaint.getText());
        if (complaint.getGift() != null){
            complaintResponse.setUserName(complaint.getGift().getUser().getFirstName());
        complaintResponse.setUserLastName(complaint.getGift().getUser().getLastName());
        complaintResponse.setUserPhoto(complaint.getGift().getUser().getPhoto());
        complaintResponse.setUserGift(complaint.getGift());
    }
        complaintResponse.setFromUserName(user.getFirstName());
        complaintResponse.setFromUserLastName(user.getLastName());
        complaintResponse.setFromUserPhoto(user.getPhoto());
        return complaintResponse;
    }
}
