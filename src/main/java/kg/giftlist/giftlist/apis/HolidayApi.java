package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.db.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/holiday")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
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
