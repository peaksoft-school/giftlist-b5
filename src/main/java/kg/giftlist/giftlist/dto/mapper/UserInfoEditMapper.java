package kg.giftlist.giftlist.dto.mapper;

import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.models.UserInfo;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoEditMapper {

    private final UserServiceImpl userService;

    public UserInfo create(UserInfoRequest userInfoRequest) {
        if (userInfoRequest == null) {
            return null;
        }
        User user = userService.getAuthenticatedUser();
        UserInfo userInfo = new UserInfo();
        userInfo.setDateOfBirth(userInfoRequest.getDateOfBirth());
        userInfo.setPhoneNumber(userInfoRequest.getPhoneNumber());
        userInfo.setClothingSize(userInfoRequest.getClothingSize());
        userInfo.setShoeSize(userInfoRequest.getShoeSize());
        userInfo.setHobby(userInfoRequest.getHobby());
        userInfo.setImportantNote(userInfoRequest.getImportantNote());
        userInfo.setInstagramLink(userInfoRequest.getInstagramLink());
        userInfo.setTelegramLink(userInfoRequest.getTelegramLink());
        userInfo.setFacebookLink(userInfoRequest.getFacebookLink());
        userInfo.setVkLink(userInfoRequest.getVkLink());
        return userInfo;
    }

        public void update(UserInfo userInfo, UserInfoRequest userInfoRequest) {
            userInfo.setCity(userInfoRequest.getCity());
            userInfo.setDateOfBirth(userInfoRequest.getDateOfBirth());
            userInfo.setPhoneNumber(userInfoRequest.getPhoneNumber());
            userInfo.setClothingSize(userInfoRequest.getClothingSize());
            userInfo.setShoeSize(userInfoRequest.getShoeSize());
            userInfo.setHobby(userInfoRequest.getHobby());
            userInfo.setImportantNote(userInfoRequest.getImportantNote());
            userInfo.setInstagramLink(userInfoRequest.getInstagramLink());
            userInfo.setTelegramLink(userInfoRequest.getTelegramLink());
            userInfo.setFacebookLink(userInfoRequest.getFacebookLink());
            userInfo.setVkLink(userInfoRequest.getVkLink());
        }

}
