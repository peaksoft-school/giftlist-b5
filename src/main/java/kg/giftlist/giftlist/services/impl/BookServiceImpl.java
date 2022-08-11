package kg.giftlist.giftlist.services.impl;

import kg.giftlist.giftlist.dto.booking.BookingRequest;
import kg.giftlist.giftlist.dto.booking.BookingResponse;
import kg.giftlist.giftlist.dto.booking.mapper.BookingEditMapper;
import kg.giftlist.giftlist.dto.gift.mapper.GiftEditMapper;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.models.Booking;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {

    private final UserServiceImpl userService;
    private final BookingEditMapper bookingEditMapper;
    private final BookingRepository bookingRepository;
    private final GiftViewMapper giftViewMapper;

    public BookingResponse create(BookingRequest bookingRequest) {
        User user = userService.getAuthenticatedUser();
        Booking booking = bookingEditMapper.create(bookingRequest);
        user.setBooking(booking);
        bookingRepository.save(booking);
        return giftViewMapper.viewBooking(booking,user);

    }






}
