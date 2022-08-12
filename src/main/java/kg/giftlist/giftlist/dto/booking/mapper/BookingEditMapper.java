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

    public Booking createGiftBooking(BookingRequest bookingRequest) {
        if (bookingRequest==null) {
            return null;
        }
        Booking booking = new Booking();
        Gift gift = giftService.findById(bookingRequest.getGiftId());
        return booking;
    }
}
