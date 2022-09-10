package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.Wish;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.HolidayService;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayEditMapper;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayViewMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.exception.WishNotFoundException;
import kg.giftlist.giftlist.db.models.Holiday;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.HolidayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayViewMapper viewMapper;
    private final HolidayEditMapper editMapper;
    private final UserRepository userRepository;
    private final WishViewMapper wishViewMapper;
    private final WishServiceImpl wishService;

    public HolidayResponse create(HolidayRequest holidayRequest) {
        Holiday holiday =editMapper.create(holidayRequest);
        if (holidayRequest.getPhoto()==null){
            holiday.setPhoto("https://giftlist-bucket.s3.amazonaws.com/1662787640327placeholder.webp");
        }else {
            holiday.setPhoto(holidayRequest.getPhoto());
        }
        User user = getAuthenticatedUser();
        holiday.setUser(user);
        holidayRepository.save(holiday);
        log.info("Holiday with id: {} successfully saved in db", holiday.getId());
        return viewMapper.viewHoliday(holiday);

    }

    public HolidayResponse update(Long id, HolidayRequest holidayRequest) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Holiday with id = " + id + " not found!"));
        editMapper.update(holiday, holidayRequest);
        log.info("Holiday with id: {} successfully updated in db", holiday.getId());
        return viewMapper.viewHoliday(holidayRepository.save(holiday));
    }

    public HolidayResponse findById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Holiday with id = " + id + " not found!"));
        return viewMapper.viewHoliday(holiday);
    }

    public SimpleResponse deleteById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Holiday with id = " + id + " not found!"));
        List<Wish> wishes = holiday.getWishes();
        for (Wish wish : wishes) {
            wishService.deleteById(wish.getId());
        }
        holidayRepository.deleteById(id);
        log.info("Holiday with id: {} successfully deleted from db", id);
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
