package kg.giftlist.giftlist.db.service;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;

import java.util.List;

public interface AdminService {

    List<AdminPageUserGetAllResponse> getAllUsers();

    SimpleResponse blockUser(Long id);

    SimpleResponse blockWish(Long wishId);

    SimpleResponse blockGift(Long giftId);

    SimpleResponse unBlockUser(Long id);

    SimpleResponse unBlockWish(Long wishId);

    SimpleResponse unBlockGift(Long giftId);

}
