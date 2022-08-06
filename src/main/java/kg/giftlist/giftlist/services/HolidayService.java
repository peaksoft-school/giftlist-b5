package kg.giftlist.giftlist.services;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;

import java.util.List;

public interface HolidayService {

    HolidayResponse create(HolidayRequest request);

    HolidayResponse update(Long id,HolidayRequest request);

    HolidayResponse findById(Long id);

    SimpleResponse deleteById(Long id);

    List<HolidayResponse> getHolidays();
}
