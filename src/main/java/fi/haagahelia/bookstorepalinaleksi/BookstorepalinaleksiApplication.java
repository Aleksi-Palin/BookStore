package fi.haagahelia.bookstorepalinaleksi;

import java.time.Year;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstorepalinaleksi.domain.Book;
import fi.haagahelia.bookstorepalinaleksi.domain.BookRepository;

@SpringBootApplication
public class BookstorepalinaleksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstorepalinaleksiApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", Year.of(1929), "1232323-21", 29.90));
			repository.save(new Book("Animal Farm", "George Orwell", Year.of(1945), "2212343-5", 19.50));

			for (Book book : repository.findAll()) {
				System.out.println(book);
			}
		};
	}
}
