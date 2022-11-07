package kg.giftlist.giftlist.dto.authentication;

import kg.giftlist.giftlist.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String photo;
    private String email;
    private String jwt;
    private Role role;
    private String message;

}
