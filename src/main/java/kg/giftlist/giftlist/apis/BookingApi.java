package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.booking.BookingResponse;
import kg.giftlist.giftlist.services.impl.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bookings")
@CrossOrigin
@Tag(name = "Booking API", description = "user with role \" User \"  can create, update or deleted booking ")
public class BookingApi {

    private final BookingServiceImpl bookingService;

    @Operation(summary = "Create booking", description = "The user can booking.")
    @PostMapping("/create/{giftId}")
    public BookingResponse create(@PathVariable Long giftId){
        return bookingService.createGiftBooking(giftId);
    }

    @Operation(summary = "Cancel booking", description = "The user can cancel booking.")
    @PostMapping("/cancel/{giftId}")
    public SimpleResponse cancelBooking(@PathVariable Long giftId){
        return bookingService.cancelBooking(giftId);
    }


}
