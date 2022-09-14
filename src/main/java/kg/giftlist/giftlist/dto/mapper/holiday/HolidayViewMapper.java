package kg.giftlist.giftlist.dto.mapper.holiday;

import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.db.models.Holiday;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class HolidayViewMapper {

    public HolidayResponse viewHoliday(Holiday holiday) {
        if (holiday == null) {
            return null;
        }
        HolidayResponse response = new HolidayResponse();
        response.setId(holiday.getId());
        response.setName(holiday.getName());
        response.setPhoto(holiday.getPhoto());
        response.setHolidayDate(holiday.getHolidayDate());
        return response;
    }

        public List<HolidayResponse> view(List<Holiday> holidays) {
            List<HolidayResponse> responses = new ArrayList<>();
            for (Holiday holiday : holidays) {
                responses.add(viewHoliday(holiday));
            }
            return responses;
        }
    }

