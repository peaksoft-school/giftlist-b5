package kg.giftlist.giftlist.services.impl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.booking.BookingResponse;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.exception.AlreadyExistException;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.exception.WishNotFoundException;
import kg.giftlist.giftlist.models.Booking;
import kg.giftlist.giftlist.models.Gift;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.BookingRepository;
import kg.giftlist.giftlist.repositories.GiftRepository;
import kg.giftlist.giftlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.ForbiddenException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl {

    private final UserRepository userRepository;
    private final GiftViewMapper giftViewMapper;
    private final GiftRepository giftRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public BookingResponse createGiftBooking(Long giftId) {
        User user = getAuthenticatedUser();
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + giftId + "not found"));
        Booking booking = new Booking();

        if (gift.getBooking()==null){
            gift.setBooking(booking);
        }else {
            throw new AlreadyExistException("Gift already booked");
        }
        if (booking.getUser()==null){
            booking.setUser(user);
        }
        if (user.getBooking()!=booking||user.getBooking()==null) {
                user.setBooking(booking);
        }else {
            throw new AlreadyExistException("Gift already booked");
        }
        if (user.getGifts().contains(gift)) {
            throw new AlreadyExistException("You can not booking your gift");
        }else {
            user.getBooking().getGifts().add(gift);
        }
        return giftViewMapper.viewBooking(booking,user);
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }

    @Transactional
    public SimpleResponse cancelBooking(Long giftId) {
        User user = getAuthenticatedUser();
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + giftId + "not found"));
        Long id = gift.getBooking().getId();
        user.getBooking().getGifts().remove(gift);
        gift.setBooking(null);
        user.setBooking(null);
        bookingRepository.deleteById(id);
        return new SimpleResponse("Canceled", "Successfully canceled ");
    }


}
