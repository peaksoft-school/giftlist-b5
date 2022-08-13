package kg.giftlist.giftlist.dto.booking.mapper;
import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Gift;
import kg.giftlist.giftlist.db.service.impl.GiftServiceImpl;
import kg.giftlist.giftlist.db.service.impl.UserServiceImpl;
import kg.giftlist.giftlist.dto.booking.BookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
