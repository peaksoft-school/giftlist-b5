package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.services.GiftService;
import kg.giftlist.giftlist.services.impl.GiftServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/gifts")
@CrossOrigin
@Tag(name = "Gift API", description = "Add a gift role \" User \"  ")
public class GiftApi {
    private final GiftServiceImpl giftService;
    @Operation(summary = "Create Gifts", description = "The user can create a gift.")
    @PostMapping("/create")
    public GiftResponse create(@RequestBody GiftRequest giftRequest){
        return giftService.create(giftRequest);
    }
    @Operation(summary = "Updates Gifts", description = "The user can update a gift. ")
    @PutMapping("/update/{id}")
    public GiftResponse update(@PathVariable Long id ,@RequestBody GiftRequest request ){
        return giftService.update(id,request);}

    @Operation(summary = "Find By Id" , description = "The user can a gift find by id. ")
    @GetMapping("/findById/{id}")
    public GiftResponse findById(@PathVariable Long id){
        return giftService.findById(id);
    }
    @Operation(summary = "Delete By Id" , description = "The user can a gift delete by id.")
    @DeleteMapping("/deleteById/{id}")
    public GiftResponse deleteById(@PathVariable Long id){
        return giftService.deleteById(id);
    }
    @Operation(summary = "GetAll Gifts", description = "The user can a gifts getAll.")
    @GetMapping("/getAllGifts/")
    public List<GiftResponse> getAll(){
        return giftService.getAll();
    }
}
