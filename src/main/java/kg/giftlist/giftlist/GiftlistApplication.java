package kg.giftlist.giftlist;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
