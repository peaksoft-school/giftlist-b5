package kg.giftlist.giftlist.services.serviceImpl;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayEditMapper;
import kg.giftlist.giftlist.dto.mapper.holiday.HolidayViewMapper;
import kg.giftlist.giftlist.exception.WishNotFoundException;
import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.HolidayRepository;
import kg.giftlist.giftlist.services.HolidayService;
import kg.giftlist.giftlist.services.impl.UserServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayViewMapper viewMapper;
    private final HolidayEditMapper editMapper;
    private final UserServiceImpl userService;

    public HolidayServiceImpl(HolidayRepository holidayRepository, HolidayViewMapper viewMapper, HolidayEditMapper editMapper, UserServiceImpl userService) {
        this.holidayRepository = holidayRepository;
        this.viewMapper = viewMapper;
        this.editMapper = editMapper;
        this.userService = userService;
    }

    public HolidayResponse create(HolidayRequest holidayRequest) {
        Holiday holiday = new Holiday(holidayRequest);
        User user = userService.getAuthenticatedUser();
        holiday.setUser(user);
        holidayRepository.save(holiday);
        return viewMapper.viewHoliday(holiday);

    }

    public HolidayResponse update(Long id, HolidayRequest holidayRequest) {
        Holiday holiday = holidayRepository.findById(id).get();
        editMapper.update(holiday, holidayRequest);
        return viewMapper.viewHoliday(holidayRepository.save(holiday));
    }

    public HolidayResponse findById(Long id) {
        Holiday holiday = holidayRepository.findById(id).get();
        return viewMapper.viewHoliday(holiday);
    }

//    public HolidayResponse deleteById(Long id) {
//        Holiday holiday = holidayRepository.getById(id);
//        holidayRepository.delete(holiday);
//        return viewMapper.viewHoliday(holiday);
//    }

    public SimpleResponse deleteById(Long id) {

        boolean exists = holidayRepository.existsById(id);

        if (!exists) {

            throw new WishNotFoundException(

                    "Holiday with id = " + id + " not found!"

            );
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
        return viewMapper.view(holidayRepository.findAll());
    }
}
