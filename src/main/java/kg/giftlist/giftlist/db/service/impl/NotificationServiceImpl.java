package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.repositories.NotificationRepository;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.user.UserInfoResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl {

    private final NotificationRepository notificationRepository;


    public GiftResponse findGiftById(Long giftId) {

        return notificationRepository.findGiftById(giftId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + giftId + " not found"));
    }

    public WishResponse findWishById(Long wishId) {

        return notificationRepository.findWishById(wishId).orElseThrow(() ->
                new NotFoundException("Wish with id: " + wishId + " not found"));
    }

    public HolidayResponse findHolidayById(Long holidayId) {

        return notificationRepository.findHolidayById(holidayId).orElseThrow(()->
                new NotFoundException("Holiday with id: " + holidayId + " not found"));
    }

    public UserInfoResponse findUserByUserId(Long userId) {
        return notificationRepository.findUserById(userId)
                .orElseThrow(() -> new NotFoundException(
                        "User with id: " + userId + " not found"
                ));
    }

    public UserInfoResponse findUserByUsername(String username) {
        return notificationRepository.findUserByUsername(username).orElseThrow(() -> new NotFoundException(
                String.format("user with username = %s does not exists", username)
        ));
    }

}
