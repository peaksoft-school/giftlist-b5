package kg.giftlist.giftlist.dto.user;

import kg.giftlist.giftlist.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String photo;
    private String jwt;
    private Role role;
}
