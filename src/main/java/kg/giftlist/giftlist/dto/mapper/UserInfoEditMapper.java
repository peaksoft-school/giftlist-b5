package kg.giftlist.giftlist.dto.mapper;
import kg.giftlist.giftlist.dto.user.UserInfoRequest;
import kg.giftlist.giftlist.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoEditMapper {

        public UserInfo create(UserInfoRequest userInfoRequest) {
            if (userInfoRequest==null) {
                    return null;
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setPhoto(userInfoRequest.getPhoto());
            userInfo.setCountry(userInfoRequest.getCountry());
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

            userInfo.setPhoto(userInfoRequest.getPhoto());
            userInfo.setCountry(userInfoRequest.getCountry());
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
