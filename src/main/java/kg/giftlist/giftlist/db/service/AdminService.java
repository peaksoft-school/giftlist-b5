package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    List<AdminPageUserGetAllResponse> getAllUsers();
    ResponseEntity<?> blockUser(Long id);
    ResponseEntity<?> unBlockUser(Long id);
}
