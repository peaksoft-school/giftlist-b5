package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.services.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gift")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Authentication API", description = "Users with role \"Admin\", \"User\" can authenticate")
public class GiftApi {

    public GiftService giftService;

    @GetMapping()
    public List<GiftResponse> giftResponses(){
       return giftService.findBy();
    }
}
