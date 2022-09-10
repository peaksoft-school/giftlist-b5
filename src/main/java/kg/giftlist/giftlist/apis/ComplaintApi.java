package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.ComplaintServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/complaints")
@CrossOrigin
@Tag(name = "Complaint API", description = "User with role \" User \"  can send complaints, but cannot see all complaints. Only Admin can see all complaints")
public class ComplaintApi {

    private final ComplaintServiceImpl complaintService;

    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Send complaint to Wish", description = "User can send complain to Admin.")
    @PostMapping("wish/{wishId}")
    public SimpleResponse sendComplaintToWish(@PathVariable Long wishId, @RequestParam String text) {
        return complaintService.sendComplaintToWish(wishId, text);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "All complains", description = "Only Admin can see all complains")
    @GetMapping
    public List<ComplaintResponse> getAllComplaints() {
        return complaintService.getComplaints();
    }

    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Send complaint to Gift", description = "User can send complaint to Admin.")
    @PostMapping("gift/{giftId}")
    public SimpleResponse sendComplaintToGift(@PathVariable Long giftId, @RequestParam String text) {
        return complaintService.sendComplaintToGift(giftId, text);
    }

    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Send complaint to Holiday", description = "User can send complaint to Admin.")
    @PostMapping("holiday/{holidayId}")
    public SimpleResponse sendComplaintToHoliday(@PathVariable Long holidayId, @RequestParam String text) {
        return complaintService.sendComplaintToHoliday(holidayId, text);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete complaint", description = "Only Admin can delete complaint.")
    @DeleteMapping("/{complaintId}")
    public SimpleResponse deleteComplaintById(@PathVariable Long complaintId) {
        return complaintService.deleteComplaintById(complaintId);
    }
}
