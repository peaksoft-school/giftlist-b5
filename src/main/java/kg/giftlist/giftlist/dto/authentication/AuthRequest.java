package kg.giftlist.giftlist.dto.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String email;
    private String password;

}