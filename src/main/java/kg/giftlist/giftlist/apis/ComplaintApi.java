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

@Tag(name = "Complaint API", description = "user with role \" User \"  can create send complaints, but cannot see all complaints\n Admin can only see all complaints")
public class ComplaintApi {

    private final ComplaintServiceImpl complaintService;

    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "send complaint to Wish", description = "The user can send complain to Admin.")
    @PostMapping("wish/{wishId}")
    public SimpleResponse sendComplaintToWish(@PathVariable Long wishId, @RequestParam String text) {
        return complaintService.sendComplaintToWish(wishId, text);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "All complains", description = "Only Admin can see all complains")
    @GetMapping("all")
    public List<ComplaintResponse> getAllComplaints(){
        return complaintService.getComplaints();
    }

    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "send complaint to Gift", description = "The user can send complaint to Admin.")
    @PostMapping("gift/{giftId}")
    public SimpleResponse sendComplaintToGift( @PathVariable Long giftId,@RequestParam String text) {
        return complaintService.sendComplaintToGift(giftId, text);
    }
}
