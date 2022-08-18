package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.db.service.impl.BookingServiceImpl;
import kg.giftlist.giftlist.dto.gift.GiftCartResponse;
import kg.giftlist.giftlist.dto.wish.WishCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bookings")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Booking API", description = "user with role \" User \"  can create, update or deleted booking ")
public class BookingApi {

    private final BookingServiceImpl bookingService;

    @Operation(summary = "Create Gift booking", description = "The user can booking only gift.")
    @PostMapping("gift-create/{giftId}")
    public GiftCartResponse createBookingGift(@PathVariable Long giftId){
        return bookingService.createBookingGift(giftId);
    }

    @Operation(summary = "Cancel Gift booking", description = "The user can cancel only gift booking.")
    @PostMapping("gift-cancel/{giftId}")
    public SimpleResponse cancelBookingGift(@PathVariable Long giftId){
        return bookingService.cancelBookingGift(giftId);
    }

    @Operation(summary = "Create Wish booking", description = "The user can booking only wish.")
    @PostMapping("wish-create/{wishId}")
    public WishCardResponse createBookingWish(@PathVariable Long wishId){
        return bookingService.createBookingWish(wishId);
    }

    @Operation(summary = "Cancel Wish booking", description = "The user can cancel only wish booking.")
    @PostMapping("wish-cancel/{wishId}")
    public SimpleResponse cancelBookingWish(@PathVariable Long wishId){
        return bookingService.cancelBookingWish(wishId);
    }

}
