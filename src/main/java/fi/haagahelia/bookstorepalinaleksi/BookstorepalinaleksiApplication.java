package fi.haagahelia.bookstorepalinaleksi;

import java.time.Year;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstorepalinaleksi.domain.Book;
import fi.haagahelia.bookstorepalinaleksi.domain.BookRepository;
import fi.haagahelia.bookstorepalinaleksi.domain.Category;
import fi.haagahelia.bookstorepalinaleksi.domain.CategoryRepository;

@SpringBootApplication
public class BookstorepalinaleksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstorepalinaleksiApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository,CategoryRepository crepository) {
		return (args) -> {
			if (repository.count() == 0){
				Category fiction = crepository.save(new Category("Fiction"));
				Category nonFiction = crepository.save(new Category("Non-fiction"));
				Category science = crepository.save(new Category("Science"));
				Category history = crepository.save(new Category("History"));



				repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", Year.of(1929), "1232323-21", 29.90,fiction));
				repository.save(new Book("Animal Farm", "George Orwell", Year.of(1945), "2212343-5", 19.50,fiction));

				for (Book book : repository.findAll()) {
					System.out.println(book);
				}
			}
			
		};
	}
}
