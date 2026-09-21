package fi.haagahelia.bookstorepalinaleksi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import fi.haagahelia.bookstorepalinaleksi.domain.User;
import fi.haagahelia.bookstorepalinaleksi.domain.UserRepository;

@SpringBootApplication
public class BookstorepalinaleksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstorepalinaleksiApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(UserRepository urepository) {
		return (args) -> {
			//User user1 = new User("user", "$2a$12$g5O9Xf8xbs7ElkGeyUVeJOaFT3BZYOyllxY9TQGV5ZtPZIgYLJaEu", "ROLE_USER");
			//User user2 = new User("admin", "$2a$12$UpKvfmHyz4MLLmuVUKr5veGlZI5YgmADhMkJ4E4gBVEQk5vgLTFqi", "ROLE_ADMIN");
			///urepository.save(user1);
			//urepository.save(user2);
			
		};
	}
}
