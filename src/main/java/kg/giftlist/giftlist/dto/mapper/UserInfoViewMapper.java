package kg.giftlist.giftlist.dto.mapper;

import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoViewMapper {

    public UserInfoResponse viewUserInfo(UserInfo userInfo, User user) {
        if (userInfo == null) {
            return null;
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setFirstName(user.getFirstName());
        userInfoResponse.setLastName(user.getLastName());
        userInfoResponse.setEmail(user.getEmail());
        userInfoResponse.setPhoto(user.getPhoto());
        userInfoResponse.setCity(userInfo.getCity());
        userInfoResponse.setDateOfBirth(userInfo.getDateOfBirth());
        userInfoResponse.setPhoneNumber(userInfo.getPhoneNumber());
        userInfoResponse.setClothingSize(userInfo.getClothingSize());
        userInfoResponse.setShoeSize(userInfo.getShoeSize());
        userInfoResponse.setHobby(userInfo.getHobby());
        userInfoResponse.setImportantNote(userInfo.getImportantNote());
        userInfoResponse.setInstagramLink(userInfo.getInstagramLink());
        userInfoResponse.setTelegramLink(userInfo.getTelegramLink());
        userInfoResponse.setFacebookLink(userInfo.getFacebookLink());
        userInfoResponse.setVkLink(userInfo.getVkLink());
        return userInfoResponse;
    }

}
