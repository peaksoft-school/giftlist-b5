package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.HolidayService;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayEditMapper;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayViewMapper;
import kg.giftlist.giftlist.exception.NotFoundException;
import kg.giftlist.giftlist.exception.WishNotFoundException;
import kg.giftlist.giftlist.db.models.Holiday;
import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.db.repositories.HolidayRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.ForbiddenException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayViewMapper viewMapper;
    private final HolidayEditMapper editMapper;
    private final UserRepository userRepository;

    public HolidayServiceImpl(HolidayRepository holidayRepository, HolidayViewMapper viewMapper, HolidayEditMapper editMapper, UserRepository userRepository) {
        this.holidayRepository = holidayRepository;
        this.viewMapper = viewMapper;
        this.editMapper = editMapper;
        this.userRepository = userRepository;
    }

    public HolidayResponse create(HolidayRequest holidayRequest) {
        Holiday holiday =editMapper.create(holidayRequest);
        User user = getAuthenticatedUser();
        holiday.setUser(user);
        holidayRepository.save(holiday);
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

    public List<HolidayResponse> view(List<Holiday> holidays) {
        List<HolidayResponse> responses = new ArrayList<>();
        for (Holiday holiday : holidays) {
            responses.add(viewMapper.viewHoliday(holiday));
        }
        return responses;
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
