package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.models.Holiday;
import kg.giftlist.giftlist.repositories.HolidayRepository;
import kg.giftlist.giftlist.services.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/holiday")
@CrossOrigin
@Tag(name = "HOLIDAY API", description = "Any user can create, update or delete holidays")
public class HolidayApi {
    private final HolidayService service;

    @PostMapping
    public HolidayResponse create(@RequestBody HolidayRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public HolidayResponse update(@PathVariable Long id, @RequestBody HolidayRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public HolidayResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<HolidayResponse> getHolidays() {
        return service.getHolidays();
    }

}
