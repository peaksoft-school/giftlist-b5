package kg.giftlist.giftlist.dto.booking.mapper;

import kg.giftlist.giftlist.dto.booking.BookingRequest;
import kg.giftlist.giftlist.models.Booking;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.services.impl.GiftServiceImpl;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingEditMapper {

    private final UserServiceImpl userService;
    private final GiftServiceImpl giftService;

    public Booking create(BookingRequest bookingRequest) {
        if (bookingRequest==null) {
            return null;
        }
        Booking booking = new Booking();
        User user = userService.findByUserId(bookingRequest.getUserId());
        Gift gift = giftService.findById(bookingRequest.getGiftId());
        booking.setUser(user);
        booking.setGifts(List.of(gift));

        return booking;
    }
}
