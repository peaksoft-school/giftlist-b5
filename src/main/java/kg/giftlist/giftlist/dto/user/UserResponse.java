package kg.giftlist.giftlist.dto.user;

import kg.giftlist.giftlist.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {
    private Long id;
    private String jwt;
    private Role role;

}
