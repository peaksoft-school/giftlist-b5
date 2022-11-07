package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.WishServiceImpl;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feeds")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Feed API", description = "Users with role \"User\" can see feed")
public class FeedApi {

    private final WishServiceImpl wishService;

    @Operation(summary = "Get friend's wishes and all wishes", description = "User can see friend's wishes and all wishes")
    @GetMapping
    public List<WishResponse> getAllWishes() {
        return wishService.getWishesForFeed();
    }

}
