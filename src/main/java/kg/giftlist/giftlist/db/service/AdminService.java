package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    List<AdminPageUserGetAllResponse> getAllUsers();
    SimpleResponse blockUser(Long id);
    SimpleResponse blockWish(Long wishId);
    SimpleResponse blockHoliday(Long holidayId);
    SimpleResponse blockGift(Long giftId);

    SimpleResponse unBlockUser(Long id);
    SimpleResponse unBlockWish(Long wishId);
    SimpleResponse unBlockHoliday(Long holidayId);
    SimpleResponse unBlockGift(Long giftId);

}
