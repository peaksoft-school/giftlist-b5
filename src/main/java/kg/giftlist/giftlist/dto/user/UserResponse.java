package kg.giftlist.giftlist.dto.user;

import kg.giftlist.giftlist.db.models.User;
import kg.giftlist.giftlist.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String jwt;
    private Role role;
    private String first_name;
    private String last_name;
    private String photo;

    public UserResponse(User user) {
        this.id = user.getId();
        this.first_name= user.getFirstName();
        this.last_name= user.getLastName();
        this.photo= user.getUserInfo().getPhoto();
    }
}
