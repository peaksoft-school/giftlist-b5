package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.Holiday;
import kg.giftlist.giftlist.db.models.Notification;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.db.repositories.HolidayRepository;
import kg.giftlist.giftlist.db.repositories.NotificationRepository;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.HolidayService;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayEditMapper;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayViewMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.enums.NotificationStatus;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.exception.WishNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayViewMapper viewMapper;
    private final HolidayEditMapper editMapper;
    private final UserRepository userRepository;
    private final WishViewMapper wishViewMapper;
    private final NotificationRepository notificationRepository;

    public HolidayResponse create(HolidayRequest holidayRequest) {
        Holiday holiday =editMapper.create(holidayRequest);
        if (holidayRequest.getPhoto()==null){
            holiday.setPhoto("https://giftlist-bucket.s3.amazonaws.com/1661860597142holiday-default-image.jpg");
        }else {
            holiday.setPhoto(holidayRequest.getPhoto());
        }
        User user = getAuthenticatedUser();
        holiday.setUser(user);
        holidayRepository.save(holiday);

        for (User fr : user.getFriends()) {
            Notification notification = new Notification();
            notification.setNotificationStatus(NotificationStatus.ADD_HOLIDAY);
            notification.setCreatedAt(LocalDate.now());
            notification.setUser(user);
            notification.setHoliday(holiday);
            notification.setRecipientId(fr.getId());
            user.addNotification(notification);
            notificationRepository.save(notification);
        }
            return viewMapper.viewHoliday(holiday);

    }

    public HolidayResponse update(Long id, HolidayRequest holidayRequest) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Holiday with id = " + id + " not found!"));
        editMapper.update(holiday, holidayRequest);
        return viewMapper.viewHoliday(holidayRepository.save(holiday));
    }

    public HolidayResponse findById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Holiday with id = " + id + " not found!"));
        return viewMapper.viewHoliday(holiday);
    }

    public SimpleResponse deleteById(Long id) {
        boolean exists = holidayRepository.existsById(id);
        if (!exists) {
            throw new WishNotFoundException("Holiday with id = " + id + " not found!");
        }
        holidayRepository.deleteById(id);
        return new SimpleResponse("Deleted!", "Holiday successfully deleted!");
    }

    public List<WishResponse> getAllHolidayWishes(Long holidayId) {
        Holiday holiday = holidayRepository.findById(holidayId).orElseThrow(() ->
                new NotFoundException("Holiday with id = " + holidayId + " not found!"));
        List<Wish> holidayWishes = holiday.getWishes();
        return wishViewMapper.getAllWishes(holidayWishes);
    }

    public List<HolidayResponse> getHolidays() {
        User user = getAuthenticatedUser();
        return viewMapper.view(holidayRepository.getAllUserHolidays(user.getId()));
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }
}
