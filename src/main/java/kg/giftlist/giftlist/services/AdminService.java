package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.user.AdminUserGetAllResponse;
import kg.giftlist.giftlist.dto.user_info.UserInfoRequest;
import kg.giftlist.giftlist.dto.user_info.UserInfoResponse;
 import org.springframework.http.ResponseEntity;


import java.util.List;

public interface AdminService {

    List<AdminUserGetAllResponse> getAllUsers();

    UserInfoResponse getUserById(Long id);
//
     ResponseEntity<?> deleteUserById(Long id);

    ResponseEntity<?> updateByIdInUser(Long id, UserInfoRequest request);

}
