package kg.giftlist.giftlist.dto.mapper;

import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.dto.mapper.gift.GiftViewMapper;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayViewMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.user.*;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.config.security.JwtUtils;
import kg.giftlist.giftlist.dto.user_friends.CommonUserProfileResponse;
import kg.giftlist.giftlist.dto.user_friends.UserFriendProfileResponse;
import kg.giftlist.giftlist.enums.FriendStatus;
import kg.giftlist.giftlist.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.ForbiddenException;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserViewMapper {

    private final JwtUtils utils;
    private final UserRepository userRepository;
    private final GiftViewMapper giftViewMapper;
    private final WishViewMapper wishViewMapper;
    private final HolidayViewMapper holidayViewMapper;

    public UserViewMapper(JwtUtils utils, UserRepository userRepository, GiftViewMapper giftViewMapper, WishViewMapper wishViewMapper, HolidayViewMapper holidayViewMapper) {
        this.utils = utils;
        this.userRepository = userRepository;
        this.giftViewMapper = giftViewMapper;
        this.wishViewMapper = wishViewMapper;
        this.holidayViewMapper = holidayViewMapper;
    }

    public UserResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoto(user.getPhoto());
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

    public UserProfileResponse viewUserProfile(User user) {
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setUserId(user.getId());
        userProfileResponse.setPhoto(user.getPhoto());
        userProfileResponse.setFirstName(user.getFirstName());
        userProfileResponse.setLastName(user.getLastName());
        userProfileResponse.setEmail(user.getEmail());
        userProfileResponse.setUserInfo(user.getUserInfo());
        return userProfileResponse;
    }

    public UserFriendProfileResponse viewFriendProfile(User user) {
        User user1 = getAuthenticatedUser();
        UserFriendProfileResponse userFriendProfileResponse = new UserFriendProfileResponse();
        userFriendProfileResponse.setUserId(user.getId());
        userFriendProfileResponse.setPhoto(user.getPhoto());
        userFriendProfileResponse.setFirstName(user.getFirstName());
        userFriendProfileResponse.setLastName(user.getLastName());
        userFriendProfileResponse.setWishCount(user.getWishes().size());
        userFriendProfileResponse.setHolidayCount(user.getHolidays().size());
        if (user1.getRequestToFriends().contains(user)) {
            userFriendProfileResponse.setFriendStatus(FriendStatus.REQUEST_TO_FRIEND);
        } else if (user1.getFriends().contains(user)) {
            userFriendProfileResponse.setFriendStatus(FriendStatus.FRIEND);
        } else {
            userFriendProfileResponse.setFriendStatus(FriendStatus.NOT_FRIEND);
        }
        return userFriendProfileResponse;
    }

    public List<UserFriendProfileResponse> getAllFriends(List<User> users) {
        List<UserFriendProfileResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(viewFriendProfile(user));
        }
        return responses;
    }

    public CommonUserProfileResponse viewCommonFriendProfile(User user) {
        User user1 = getAuthenticatedUser();
        CommonUserProfileResponse commonUserProfileResponse = new CommonUserProfileResponse();
        commonUserProfileResponse.setId(user.getId());
        commonUserProfileResponse.setFirstName(user.getFirstName());
        commonUserProfileResponse.setLastName(user.getLastName());
        commonUserProfileResponse.setEmail(user.getEmail());
        commonUserProfileResponse.setPhoto(user.getPhoto());
        commonUserProfileResponse.setUserInfo(user.getUserInfo());
        List<Wish> wishes = user.getWishes();
        List<Wish> sortWishes = new ArrayList<>();
        for (Wish wish : wishes) {
            if (user1.getRole().equals(Role.USER)) {
                if (wish.getIsBlock().equals(false)) {
                    sortWishes.add(wish);
                }
            } else if (user1.getRole().equals(Role.ADMIN)) {
                sortWishes.add(wish);
            }
        }
        commonUserProfileResponse.setWishes(wishViewMapper.getAllWishes(sortWishes));
        commonUserProfileResponse.setHolidays(holidayViewMapper.view(user.getHolidays()));

        List<Gift> gifts = user.getGifts();
        List<Gift> sortGifts = new ArrayList<>();
        for (Gift gift : gifts) {
            if (user1.getRole().equals(Role.USER)) {
                if (gift.getIsBlock().equals(false)) {
                    sortGifts.add(gift);
                }
            } else if (user1.getRole().equals(Role.ADMIN)) {
                sortGifts.add(gift);
            }
        }
        commonUserProfileResponse.setGifts(giftViewMapper.getAllGifts(sortGifts));
        commonUserProfileResponse.setIsBlock(user.getIsBlock());
        if (user1.getRequestToFriends().contains(user)) {
            commonUserProfileResponse.setFriendStatus(FriendStatus.REQUEST_TO_FRIEND);
        } else if (user1.getFriends().contains(user)) {
            commonUserProfileResponse.setFriendStatus(FriendStatus.FRIEND);
        } else if (user.getRequestToFriends().contains(user1)) {
            commonUserProfileResponse.setFriendStatus(FriendStatus.REQUESTED);
        } else {
            commonUserProfileResponse.setFriendStatus(FriendStatus.NOT_FRIEND);
        }
        return commonUserProfileResponse;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }

}