package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.repositories.ComplaintRepository;
import kg.giftlist.giftlist.db.service.impl.BookingServiceImpl;
import kg.giftlist.giftlist.db.service.impl.ComplaintServiceImpl;
import kg.giftlist.giftlist.dto.booking.BookingResponse;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.holiday.HolidayRequest;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintRequest;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/complaints")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Complaint API", description = "user with role \" User \"  can create send complaints")
public class ComplaintApi {

    private final ComplaintServiceImpl complaintService;


    @Operation(summary = "send complaint to Wish", description = "The user can send complain to Admin.")
    @PostMapping("wish/{wishId}")
    public ComplaintResponse sendComplaintToWish( @PathVariable Long wishId, @RequestParam String text) {
        return complaintService.sendComplaintToWish(wishId, text);
    }

    @GetMapping("toWishes")
    public List<ComplaintResponse> getAllComplaints(){
        return complaintService.getComplaints();
    }

    @Operation(summary = "send complaint to Gift", description = "The user can send complain to Admin.")
    @PostMapping("gift/{giftId}")
    public ComplaintResponse sendComplaintToGift( @PathVariable Long giftId,@RequestParam String text) {
        return complaintService.sendComplaintToGift(giftId, text);
    }
}
