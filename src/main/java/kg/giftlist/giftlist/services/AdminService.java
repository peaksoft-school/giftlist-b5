package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    List<AdminPageUserGetAllResponse> getAllUsers();

    ResponseEntity<?> blockUser(Long id);
    ResponseEntity<?> unBlockUser(Long id);
    ResponseEntity<?> blockGift(Long id);
    ResponseEntity<?> blockHoliday(Long id);
    ResponseEntity<?> blockWish(Long id);
}
