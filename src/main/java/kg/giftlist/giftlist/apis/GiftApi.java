package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.GiftServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/gifts")
@CrossOrigin
@Tag(name = "Gift API", description = "Add a gift role \" User \"  can create, update or deleted gifts ")
public class GiftApi {

    private final GiftServiceImpl giftService;

    @Operation(summary = "Create Gifts", description = "The user can create a gift.")
    @PostMapping("/create")
    public GiftResponse create(@RequestBody GiftRequest giftRequest){
        return giftService.create(giftRequest);
    }

    @Operation(summary = "Updates Gifts", description = "The user can update a gift. ")
    @PutMapping("/update/{giftId}")
    public GiftResponse update(@PathVariable Long giftId, @RequestBody GiftRequest request ){
        return giftService.update(giftId,request);
    }

    @Operation(summary = "Delete By Id" , description = " delete by gift id.")
    @DeleteMapping("/delete/{giftId}")
    public SimpleResponse deleteById(@PathVariable Long giftId){
        return giftService.deleteById(giftId);
    }

    @Operation(summary = "Find by gift id", description = "The user can find gift.")
    @GetMapping("/{giftId}")
    public GiftResponse findById(@PathVariable Long giftId){
        return giftService.getGiftById(giftId);
    }

    @Operation(summary = "Get all Gifts", description = "get all gifts.")
    @GetMapping("/getAllGifts/")
    public List<GiftResponse> getAllGifts(){
        return giftService.getAll();
    }


}
