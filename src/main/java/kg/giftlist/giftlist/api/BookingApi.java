package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.db.service.impl.BookingServiceImpl;
import kg.giftlist.giftlist.dto.gift.GiftCartResponse;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.wish.WishCardResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bookings")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Booking API", description = "User with role \"User\" can create, update or deleted booking")
public class BookingApi {

    private final BookingServiceImpl bookingService;

    @Operation(summary = "Create Gift booking", description = "User can booking only gift.")
    @PostMapping("gift-create/{giftId}")
    public GiftCartResponse createBookingGift(@PathVariable Long giftId) {
        return bookingService.createBookingGift(giftId);
    }

    @Operation(summary = "Cancel Gift booking", description = "User can cancel only gift booking.")
    @PostMapping("gift-cancel/{giftId}")
    public SimpleResponse cancelBookingGift(@PathVariable Long giftId) {
        return bookingService.cancelBookingGift(giftId);
    }

    @Operation(summary = "Create Wish booking", description = "User can booking only wish.")
    @PostMapping("wish-create/{wishId}")
    public WishCardResponse createBookingWish(@PathVariable Long wishId) {
        return bookingService.createBookingWish(wishId);
    }

    @Operation(summary = "Cancel Wish booking", description = "User can cancel only wish booking.")
    @PostMapping("wish-cancel/{wishId}")
    public SimpleResponse cancelBookingWish(@PathVariable Long wishId) {
        return bookingService.cancelBookingWish(wishId);
    }

    @Operation(summary = "Get all booked wishes", description = "User can get own booked wishes.")
    @GetMapping("wishes")
    public List<WishResponse> getAllBookedWishes() {
        return bookingService.getAllBookedWishes();
    }

    @Operation(summary = "Get all booked gifts", description = "User can get own booked gifts.")
    @GetMapping("gifts")
    public List<GiftResponse> getAllBookedGifts() {
        return bookingService.getAllBookedGift();
    }

}
