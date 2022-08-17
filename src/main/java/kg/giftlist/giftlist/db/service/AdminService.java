package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    List<AdminPageUserGetAllResponse> getAllUsers();
    SimpleResponse blockUser(Long id);
    SimpleResponse unBlockUser(Long id);
}
