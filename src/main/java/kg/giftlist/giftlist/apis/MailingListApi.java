package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.repositories.UserRepository;
import kg.giftlist.giftlist.db.service.impl.MailingListServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.mailing_list.SendMailingRequest;
import kg.giftlist.giftlist.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
    private final UserRepository userRepository;

    @Operation(summary = "Send mailing", description = "Admin can send mailing")
    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody SendMailingRequest sendMailingRequest) {
        mailingListService.send( sendMailingRequest );
        return ResponseEntity.ok( HttpStatus.OK );
    }

    @Operation(summary = "Send mailing", description = "Admin can send mailing")
    @PostMapping("/send/fg")
    public SimpleResponse password(@RequestParam String to,@RequestParam String link) throws Exception {
        if (!userRepository.existsByEmail( to )) {
            return new SimpleResponse( "WRONG", "This email is not in the database!" );
        }
        mailingListService.sentLink( to,link );
        return new SimpleResponse( "OK","Successfully sent to your email" );
    }
}