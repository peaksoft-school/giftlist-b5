package kg.giftlist.giftlist.dto.mapper;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.UserInfo;
import kg.giftlist.giftlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInfoViewMapper {

    private final UserRepository userRepository;


    public UserInfoResponse viewUserInfo(UserInfo userInfo) {
        if (userInfo==null) {
            return null;
        }

        User user = getAuthenticatedUser();
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setFirstName(user.getFirstName());
        userInfoResponse.setLastName(user.getLastName());
        userInfoResponse.setEmail(user.getEmail());
        userInfoResponse.setPhoto(userInfo.getPhoto());
        userInfoResponse.setCountry(userInfo.getCountry());
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

    public List<UserInfoResponse> viewAllUsers(List<UserInfo> userInfo) {
        List<UserInfoResponse> userInfoResponses = new ArrayList<>();
        for (UserInfo userInfoRes : userInfo) {
            userInfoResponses.add(viewUserInfo(userInfoRes));
        }
        return userInfoResponses;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("Username not found "));
    }
}
