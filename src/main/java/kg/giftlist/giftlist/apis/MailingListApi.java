package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.MailingListServiceImpl;
import kg.giftlist.giftlist.dto.mailing_list.SendMailingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mailing")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Mailing API", description = "User with role \" Admin \" can send mailing to user's email")
public class MailingListApi {

    private final MailingListServiceImpl mailingListService;

    @Operation(summary = "Send mailing", description = "Admin can send mailing")
    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody SendMailingRequest sendMailingRequest) {
        mailingListService.send( sendMailingRequest );
        return ResponseEntity.ok( HttpStatus.OK );
    }
}