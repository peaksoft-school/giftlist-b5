package kg.giftlist.giftlist;

import kg.giftlist.giftlist.enums.FriendStatus;
import kg.giftlist.giftlist.enums.Role;
import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@SpringBootApplication
public class GiftlistApplication {

	private final PasswordEncoder encoder;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(GiftlistApplication.class, args);
		System.out.println("Welcome colleagues, project name is Giftlist!");
	}

	@GetMapping("/")
	public String greetingPage() {
		return "<h1>Welcome to Giftlist application!!!<h1/>";
	}

	@PostConstruct
	public void init() {

		User user = new User();
		user.setFirstName("User");
		user.setLastName("Userova");
		user.setEmail("user@gmail.com");
		user.setPassword(encoder.encode("users"));
		user.setRole(Role.USER);
		user.setFriendStatus(FriendStatus.FRIEND);
		user.setIsBlock(true);
		userRepository.save(user);

		User admin = new User();
		admin.setFirstName("Admin");
		admin.setLastName("Adminov");
		admin.setEmail("admin@gmail.com");
		admin.setPassword(encoder.encode("admin"));
		admin.setRole(Role.ADMIN);
		admin.setFriendStatus(FriendStatus.NOT_FRIEND);
		admin.setIsBlock(true);
		userRepository.save(admin);

	}
}