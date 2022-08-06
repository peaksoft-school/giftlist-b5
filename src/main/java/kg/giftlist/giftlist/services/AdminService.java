package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.user.AdminPageUserGetAllResponse;

import java.util.List;

public interface AdminService {

    List<AdminPageUserGetAllResponse> getAllUsers();
}
