package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.booking.BookingResponse;
import kg.giftlist.giftlist.db.service.impl.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bookings")
@CrossOrigin
@Tag(name = "Booking API", description = "user with role \" User \"  can create, update or deleted booking ")
public class BookingApi {

    private final BookingServiceImpl bookingService;

    @Operation(summary = "Create Gift booking", description = "The user can booking only gift.")
    @PostMapping("/create/{giftId}")
    public BookingResponse createBookingGift(@PathVariable Long giftId){
        return bookingService.createBookingGift(giftId);
    }

    @Operation(summary = "Cancel Gift booking", description = "The user can cancel only gift booking.")
    @PostMapping("/cancel/{giftId}")
    public SimpleResponse cancelBookingGift(@PathVariable Long giftId){
        return bookingService.cancelBookingGift(giftId);
    }

}
