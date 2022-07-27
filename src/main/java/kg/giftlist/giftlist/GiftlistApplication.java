package kg.giftlist.giftlist;

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

@RestController
@AllArgsConstructor
@SpringBootApplication
public class GiftlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftlistApplication.class, args);
		System.out.println("Welcome colleagues, project name is Giftlist!");
	}

	@GetMapping("/")
	public String greetingPage(){
		return "<h1>Welcome to Giftlist application!!!<h1/>";
	}
}
