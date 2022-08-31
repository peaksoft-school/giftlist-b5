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
    private String firstName;
    private String lastName;
    private String photo;
    private String jwt;
    private Role role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName= user.getFirstName();
        this.lastName= user.getLastName();
        this.photo= user.getPhoto();
    }
}
