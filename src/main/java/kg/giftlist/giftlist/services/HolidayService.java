package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.models.Holiday;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HolidayService {

    HolidayResponse create(HolidayRequest request);

    HolidayResponse update(Long id,HolidayRequest request);

    HolidayResponse findById(Long id);

    HolidayResponse deleteById(Long id);

    List<HolidayResponse> getHolidays();

    List<Holiday> searchByName(String keyword);


}
