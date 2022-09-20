package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.GiftServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/gifts")
@CrossOrigin
@Tag(name = "Gift API", description = "User with role \"User, Admin\"  can create, update or delete gifts")
public class GiftApi {

    private final GiftServiceImpl giftService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Create gift", description = "The user can create a gift")
    @PostMapping
    public GiftResponse create(@RequestBody GiftRequest giftRequest) {
        return giftService.create(giftRequest);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Update gift", description = "The user can update a gift ")
    @PutMapping("/{giftId}")
    public GiftResponse update(@PathVariable Long giftId, @RequestBody GiftRequest request) {
        return giftService.update(giftId, request);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Delete gift", description = " User can delete by gift id")
    @DeleteMapping("/{giftId}")
    public SimpleResponse deleteById(@PathVariable Long giftId) {
        return giftService.deleteById(giftId);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Find gift by id", description = "User can find gift")
    @GetMapping("/{giftId}")
    public GiftResponse findById(@PathVariable Long giftId) {
        return giftService.getGiftById(giftId);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Get all own gifts", description = "User can get only own all gifts")
    @GetMapping("my-gifts")
    public List<GiftResponse> getAllOwnGifts() {
        return giftService.getAllOwnGifts();
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Get all gifts", description = "User can get all gifts")
    @GetMapping
    public List<GiftResponse> getAllGifts() {
        return giftService.getAllGiftsForUser();
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "Search gifts by filter", description = "User can search gifts by filter")
    @GetMapping("/filter")
    public List<GiftResponse> filter(
            @RequestParam(required = false,defaultValue = "all") String search,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long subCategoryId){
        return giftService.filter(search,status,categoryId,subCategoryId);
    }
}
