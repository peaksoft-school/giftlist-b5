package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.HolidayServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.holiday.HolidayResponse;
import kg.giftlist.giftlist.db.service.HolidayService;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/holiday")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Holiday API", description = "User with role \"User\"  can create, update or delete holidays")
public class HolidayApi {

    private final HolidayService service;
    private final HolidayServiceImpl holidayService;

    @Operation(summary = "Create holiday", description = "User can create a holiday")
    @PostMapping
    public HolidayResponse create(@RequestBody HolidayRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Update holiday", description = "User can update a holiday")
    @PutMapping("/{id}")
    public HolidayResponse update(@PathVariable Long id, @RequestBody HolidayRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Get holiday", description = "User can get holiday")
    @GetMapping("/{id}")
    public HolidayResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Delete holiday", description = "User can delete holiday")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Get all holidays", description = "User can get all holidays")
    @GetMapping
    public List<HolidayResponse> getHolidays() {
        return service.getHolidays();
    }

    @Operation(summary = "Get all holiday's wishes", description = "User can get all holiday's wishes")
    @GetMapping("wishes/{holidayId}")
    public List<WishResponse> getAllHolidayWishes(@PathVariable Long holidayId) {
        return holidayService.getAllHolidayWishes(holidayId);
    }

}
