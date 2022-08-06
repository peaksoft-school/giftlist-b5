package kg.giftlist.giftlist.dto.mapper;

import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.models.UserInfo;
import kg.giftlist.giftlist.security.JwtUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserViewMapper {

    private final JwtUtils utils;

    public UserViewMapper(JwtUtils utils) {
        this.utils = utils;
    }

    public UserResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        String jwt = utils.generateJwt(user);
        response.setJwt(jwt);
        response.setRole(user.getRole());
        return response;
    }

    public List<UserResponse> view(List<User> users) {
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(viewUser(user));
        }
        return responses;
    }

    public UserFirstProfileResponse viewUserFirstProfile(User user) {
        UserFirstProfileResponse userFirstProfileResponse = new UserFirstProfileResponse();
        userFirstProfileResponse.setFirstName(user.getFirstName());
        userFirstProfileResponse.setLastName(user.getLastName());
        userFirstProfileResponse.setEmail(user.getEmail());
        userFirstProfileResponse.setPhoto(user.getPhoto());
        return userFirstProfileResponse;
    }

    public UserProfileResponse viewUserProfile(User user) {
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setFirstName(user.getFirstName());
        userProfileResponse.setLastName(user.getLastName());
        userProfileResponse.setEmail(user.getEmail());
        userProfileResponse.setUserInfo(user.getUserInfo());
        return userProfileResponse;
    }

    public UserPasswordChangedResponse vieNewPassword(User user) {
        UserPasswordChangedResponse request = new UserPasswordChangedResponse();
        request.setPassword(user.getPassword());
        return request;
    }


}