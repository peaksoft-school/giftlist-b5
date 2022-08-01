package kg.giftlist.giftlist.dto.mapper;

import kg.giftlist.giftlist.dto.user.AdminUserGetAllResponse;
import kg.giftlist.giftlist.dto.user.UserRequest;
import kg.giftlist.giftlist.dto.user_info.UserInfoResponse;
import kg.giftlist.giftlist.enums.Role;
import kg.giftlist.giftlist.models.MailingList;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.MailingListRepository;
import kg.giftlist.giftlist.services.impl.MailingListServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEditMapper {

    private final MailingList mailingList;
    private final MailingListServiceImpl mailingListService;

    public User create(UserRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);
        if (request.isMailingList()) {
            mailingList.setEmail(request.getEmail());
            mailingListService.save(mailingList);
        }
        return user;
    }

    public void update(User user, UserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
    }

    public AdminUserGetAllResponse createUser(User user) {
        if (user == null) {
            return null;
        }
        AdminUserGetAllResponse adminUserGetAllResponse = new AdminUserGetAllResponse();
        adminUserGetAllResponse.setId(user.getUserInfo().getId());
        adminUserGetAllResponse.setCountGift(user.getGifts().size());
        adminUserGetAllResponse.setFirst_name(user.getFirstName());
        adminUserGetAllResponse.setLast_name(user.getLastName());
        adminUserGetAllResponse.setPhoto(user.getUserInfo().getPhoto());

        return adminUserGetAllResponse;
    }
}