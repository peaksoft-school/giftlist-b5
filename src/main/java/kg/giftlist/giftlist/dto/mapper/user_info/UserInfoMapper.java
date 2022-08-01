package kg.giftlist.giftlist.dto.mapper.user_info;

import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.user_info.UserInfoResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.models.UserInfo;
import kg.giftlist.giftlist.models.Wish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInfoMapper {

    public UserInfoResponse create(UserInfo userInfo){

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setId(userInfo.getId());
        userInfoResponse.setTelegramLink(userInfo.getTelegramLink());
        userInfoResponse.setVkLink(userInfo.getVkLink());
        userInfoResponse.setShoeSize(userInfo.getShoeSize());
        userInfoResponse.setPhoneNumber(userInfo.getPhoneNumber());
        userInfoResponse.setInstagramLink(userInfo.getInstagramLink());
        userInfoResponse.setImportantNote(userInfo.getImportantNote());
        userInfoResponse.setPhoto(userInfo.getPhoto());
        userInfoResponse.setFacebookLink(userInfo.getFacebookLink());
        userInfoResponse.setHobby(userInfo.getHobby());
        userInfoResponse.setEmail(userInfo.getUser().getEmail());
        userInfoResponse.setCountry(userInfo.getCountry());
        userInfoResponse.setClothingSize(userInfo.getClothingSize());
        userInfoResponse.setFirst_name(userInfo.getUser().getFirstName());
        userInfoResponse.setFirst_name(userInfo.getUser().getLastName());
        List<Gift> gifts = userInfo.getUser().getGifts();
        userInfoResponse.setGifts(viewGiftList(gifts));
        List<Holiday> holidays = userInfo.getUser().getHolidays();
        userInfoResponse.setHoliday(view(holidays));
        List<Wish> wishes = userInfo.getUser().getWishes();
        userInfoResponse.setWishes(viewWishList(wishes));
        userInfoResponse.setDateOfBirth(userInfo.getDateOfBirth());

        return userInfoResponse;
    }
   // view holiday
    public HolidayResponse viewHoliday(Holiday holiday) {
        HolidayResponse response = new HolidayResponse();
        response.setId(holiday.getId());
        response.setName(holiday.getName());
        response.setPhoto(holiday.getPhoto());
        response.setHolidayDate(holiday.getHolidayDate());

        return response;
    }
    public List<HolidayResponse> view(List<Holiday> holidays) {
        List<HolidayResponse> responses = new ArrayList<>();
        for (Holiday holiday : holidays) {
            responses.add(viewHoliday(holiday));
        }
        return responses;
    }

    // view gift
    public GiftResponse viewGift(Gift gift) {
        GiftResponse response = new GiftResponse();
        response.setId(gift.getId());
        response.setGiftPhoto(gift.getPhoto());
        response.setGiftName(gift.getName());
        response.setStatus(gift.getStatus());
        response.setGiftDate(gift.getDate());
        response.setBooking_user_id(gift.getUser().getId());
        return response;
    }
    public List<GiftResponse> viewGiftList(List<Gift> gifts) {
        List<GiftResponse> responses = new ArrayList<>();
        for (Gift gift : gifts) {
            responses.add(viewGift(gift));
        }
        return responses;
    }

    // view wishes
    public WishResponse viewWish(Wish wish) {
        WishResponse response = new WishResponse();
        response.setId(wish.getId());
        response.setWishName(wish.getGiftName());
        response.setIsBlock(wish.getIsBlock());
        response.setLocalDate(wish.getWishDate());
        response.setPhoto(wish.getGiftPhoto());
        response.setHolidayName(wish.getHolidays().getName());
        return response;
    }
    public List<WishResponse> viewWishList(List<Wish> wishes) {
        List<WishResponse> responses = new ArrayList<>();
        for (Wish wish : wishes) {
            responses.add(viewWish(wish));
        }
        return responses;
    }
}
