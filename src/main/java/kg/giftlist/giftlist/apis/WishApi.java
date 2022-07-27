package kg.giftlist.giftlist.apis;

import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.wish.WishRequest;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.services.impl.WishServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/wish")
@PreAuthorize("hasAnyAuthority('USER')")
public class WishApi {

    private final WishServiceImpl wishService;

    @PostMapping("/save")
    public WishResponse create(@RequestBody WishRequest request){

        return wishService.create(request);

    }

    @PutMapping("/{id}")
    public WishResponse update(@PathVariable Long id,
                                  @RequestBody WishRequest request) {
        return wishService.update(id, request);

    }

    @GetMapping("/find/{id}")
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
