package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.services.impl.WishServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/wish")
//@PreAuthorize("hasAnyAuthority('USER')")
@CrossOrigin
@Tag(name = "WISH API", description = "Any user can create, update or delete wishes!")
public class WishApi {

    private final WishServiceImpl wishService;

    @PostMapping
    public WishResponse create(@RequestBody WishRequest request){

        return wishService.create(request);

    }

    @PutMapping("/{id}")
    public WishResponse update(@PathVariable Long id,
                                  @RequestBody WishRequest request, @RequestBody User user) {
        return wishService.update(id, request, user);

    }

    @GetMapping("/{id}")
    public WishResponse findById(@PathVariable Long id){

        return wishService.findById(id);

    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){

        return wishService.deleteById(id);

    }

    @GetMapping
    public List<WishResponse> getAllWishes(){

        return wishService.getAllWishes();

    }
}
