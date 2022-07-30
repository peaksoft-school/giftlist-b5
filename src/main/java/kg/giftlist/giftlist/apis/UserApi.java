package kg.giftlist.giftlist.apis;

import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserApi {

    private final UserService userService;

    @GetMapping("/search/{keyword}")
    List<User> search(@PathVariable String keyword){
        return userService.search(keyword);
    }
}
