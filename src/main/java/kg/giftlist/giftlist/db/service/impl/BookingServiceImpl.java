package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.*;
import kg.giftlist.giftlist.db.repositories.*;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftCartResponse;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.wish.WishCardResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.enums.NotificationStatus;
import kg.giftlist.giftlist.exception.AlreadyExistException;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.exception.handler.GiftForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.ForbiddenException;
import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookingServiceImpl {

    private final UserRepository userRepository;
    private final GiftViewMapper giftViewMapper;
    private final GiftRepository giftRepository;
    private final WishRepository wishRepository;
    private final WishViewMapper wishViewMapper;
    private final BookingRepository bookingRepository;
    private final NotificationRepository notificationRepository;

    @Transactional
    public GiftCartResponse createBookingGift(Long giftId) {
        User user = getAuthenticatedUser();

        if (user.getBooking()==null) {
            Booking booking  = new Booking();
            user.setBooking(booking);
            booking.setUser(user);
        }
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + giftId + "not found"));
        Booking booking = user.getBooking();
        if (gift.getBooking()==null) {
            gift.setBooking(booking);
            log.info("Gift with id: {} successfully booked with id: {}", gift.getId(), booking.getId());
        }else {
            log.error("Gift already booked");
            throw new AlreadyExistException("Gift already booked");
        }
        if (user.getGifts().contains(gift)) {
            log.warn("You can not booking your gift");
            throw new GiftForbiddenException("You can not booking your gift");
        }else {
            user.getBooking().getGifts().add(gift);
        }

        for (User fr : user.getFriends()) {
            Notification notification = new Notification();
            notification.setNotificationStatus(NotificationStatus.ADD_GIFT_BOOKING);
            notification.setCreatedAt(LocalDate.now());
            notification.setUser(user);
            notification.setGiftBooking(booking);
            notification.setGift(gift);
            notification.setRecipientId(fr.getId());
            user.addNotification(notification);

            notificationRepository.save(notification);
        }
        return giftViewMapper.viewGiftCard(gift);
    }

    @Transactional
    public SimpleResponse cancelBookingGift(Long giftId) {
        User user = getAuthenticatedUser();
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                 new NotFoundException("Gift with id: " + giftId + "not found"));
        if (gift.getBooking().equals(user.getBooking())) {
            user.getBooking().getGifts().remove(gift);
            gift.setBooking(null);
            log.info("Gift with id: {} successfully cancel booking", gift.getId());
        }else if(user.getGifts().contains(gift)) {
            User bookedUser = gift.getBooking().getUser();
            bookedUser.getBooking().getGifts().remove(gift);
            gift.setBooking(null);
        }else {
            log.warn("You can cancel only own booking or only your gift");
            throw new AlreadyExistException("You can cancel only own booking or only your gift");
        }
        return new SimpleResponse("Canceled", "Successfully canceled ");
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }

    @Transactional
    public WishCardResponse createBookingWish(Long wishId) {
        User user = getAuthenticatedUser();
        if (user.getBooking()==null) {
            Booking booking  = new Booking();
            user.setBooking(booking);
            booking.setUser(user);
        }
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException("Wish with id: " + wishId + "not found"));
        Booking booking = user.getBooking();
        if (wish.getBooking()==null) {
            wish.setBooking(booking);
            log.info("Wish with id: {} successfully booked with id: {}", wish.getId(), booking.getId());
        }else {
            log.warn("Wish already booked");
            throw new AlreadyExistException("Wish already booked");
        }
        if (user.getWishes().contains(wish)) {
            log.warn("You can not booking your wish");
            throw new GiftForbiddenException("You can not booking your wish");
        }else {
            user.getBooking().getWishes().add(wish);
        }
        for (User fr : user.getFriends()) {
            Notification notification = new Notification();
            notification.setNotificationStatus(NotificationStatus.ADD_WISH_BOOKING);
            notification.setCreatedAt(LocalDate.now());
            notification.setUser(user);
            notification.setWishBooking(booking);
            notification.setWish(wish);
            notification.setRecipientId(fr.getId());
            user.addNotification(notification);

            notificationRepository.save(notification);
        }

        return wishViewMapper.viewWish(wish);
    }

    @Transactional
    public SimpleResponse cancelBookingWish(Long wishId) {
        User user = getAuthenticatedUser();
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException("Wish with id: " + wishId + "not found"));
        if (wish.getBooking().equals(user.getBooking())) {
            user.getBooking().getWishes().remove(wish);
            wish.setBooking(null);
            log.info("Wish with id: {} successfully cancel booking", wish.getId());
        }else {
            log.warn("You can cancel only own booking");
            throw new AlreadyExistException("You can cancel only own booking");
        }
        return new SimpleResponse("Canceled", "Successfully canceled ");
    }

    public List<WishResponse> getAllBookedWishes() {
        User user = getAuthenticatedUser();
        return wishViewMapper.getAllWishes(bookingRepository.getAllUserBookingWishes(user.getId()));
    }

    public List<GiftResponse> getAllBookedGift() {
        User user = getAuthenticatedUser();
        return giftViewMapper.getAllGifts(bookingRepository.getAllUserBookingGifts(user.getId()));
    }



}
