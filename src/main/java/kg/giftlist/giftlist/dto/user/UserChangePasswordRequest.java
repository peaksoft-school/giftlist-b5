package kg.giftlist.giftlist.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}
