package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.services.impl.WishServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/wish")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@Tag(name = "WISH LIST API", description = "User can create, update or delete wishes!")
public class WishApi {

    private final WishServiceImpl wishService;

    @Operation(summary = "Create wishlist", description = "User can create wishlist")
    @PostMapping
    public WishResponse create(@RequestBody WishRequest request) {
        return wishService.create(request);
    }

    @Operation(summary = "Update wishlist", description = "User can update information only own wishlist, which  was created before")
    @PutMapping("/{wishId}")
    public WishResponse update(@PathVariable Long id, @RequestBody WishRequest request) {
        return wishService.update(id, request);
    }

    @Operation(summary = "Find by ID", description = "User can search wish by ID")
    @GetMapping("/{wishId}")
    public WishResponse findById(@PathVariable Long id) {
        return wishService.findById(id);
    }

    @Operation(summary = "Delete wishlist", description = "User can delete wishlist, when we delete wish, holiday and user will not be deleted")
    @DeleteMapping("/{wishId}")
    public SimpleResponse delete(@PathVariable Long id) {
        return wishService.deleteById(id);
    }

    @GetMapping
    public List<WishResponse> getAllWishes() {
        return wishService.getAllWishes();
    }
}
