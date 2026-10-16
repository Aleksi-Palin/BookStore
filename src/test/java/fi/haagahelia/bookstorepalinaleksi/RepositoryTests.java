package fi.haagahelia.bookstorepalinaleksi;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Year;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import fi.haagahelia.bookstorepalinaleksi.domain.Book;
import fi.haagahelia.bookstorepalinaleksi.domain.BookRepository;
import fi.haagahelia.bookstorepalinaleksi.domain.Category;
import fi.haagahelia.bookstorepalinaleksi.domain.CategoryRepository;
import fi.haagahelia.bookstorepalinaleksi.domain.User;
import fi.haagahelia.bookstorepalinaleksi.domain.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createSearchAndDeleteBook() {
        Category category = categoryRepository.save(new Category("Fiction"));
        Book book = bookRepository.save(
                new Book("The Hobbit", "J. R. R. Tolkien", Year.of(1937), "9780261102217", 12.50, category));

        assertThat(book.getId()).isNotNull();
        assertThat(bookRepository.findById(book.getId())).contains(book);

        bookRepository.deleteById(book.getId());

        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }

    @Test
    void createSearchAndDeleteCategory() {
        Category category = categoryRepository.save(new Category("Science fiction"));

        assertThat(category.getCategoryid()).isNotNull();
        assertThat(categoryRepository.findById(category.getCategoryid())).contains(category);

        categoryRepository.deleteById(category.getCategoryid());

        assertThat(categoryRepository.findById(category.getCategoryid())).isEmpty();
    }

    @Test
    void createSearchAndDeleteUser() {
        User user = userRepository.save(new User("repository-test-user", "encoded-password", "ROLE_USER"));

        assertThat(user.getId()).isNotNull();
        assertThat(userRepository.findByUsername("repository-test-user")).isSameAs(user);

        userRepository.deleteById(user.getId());

        assertThat(userRepository.findByUsername("repository-test-user")).isNull();
    }
}
