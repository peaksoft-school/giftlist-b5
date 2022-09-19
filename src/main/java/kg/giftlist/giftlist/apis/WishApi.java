package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.db.service.impl.WishServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/wish")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Wish API", description = "Users with role  \"User\"  can create, update or delete wish")
public class WishApi {

    private final WishServiceImpl wishService;

    @Operation(summary = "Create wish", description = "User can create wish")
    @PostMapping
    public WishResponse create(@RequestBody WishRequest request) {
        return wishService.create(request);
    }

    @Operation(summary = "Update wish", description = "User can update information only own wish, which was created before")
    @PutMapping("/{wishId}")
    public WishResponse update(@PathVariable Long wishId, @RequestBody WishRequest request) {
        return wishService.update(wishId, request);
    }

    @Operation(summary = "Get wish", description = "User can get wish by id")
    @GetMapping("/{wishId}")
    public WishResponse findById(@PathVariable Long wishId) {
        return wishService.findById(wishId);
    }

    @Operation(summary = "Delete wish", description = "User can delete wishlist, when we delete wish, holiday and user will not be deleted")
    @DeleteMapping("/{wishId}")
    public SimpleResponse delete(@PathVariable Long wishId) {
        return wishService.deleteById(wishId);
    }

    @Operation(summary = "Get all wishes", description = "User can get all wishes")
    @GetMapping
    public List<WishResponse> getAllWishes() {
        return wishService.getAllWishes();
    }

    @Operation(summary = "Add friend's wish to my wish", description = "User can add friend's wish to own wish")
    @PostMapping("add/{wishId}")
    public WishResponse addFriendWishToMyWish(@PathVariable Long wishId) {
        return wishService.addToMyWish(wishId);
    }
}
