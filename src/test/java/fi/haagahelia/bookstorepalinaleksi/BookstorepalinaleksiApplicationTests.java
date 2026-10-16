package fi.haagahelia.bookstorepalinaleksi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import fi.haagahelia.bookstorepalinaleksi.web.BookController;
import fi.haagahelia.bookstorepalinaleksi.web.BookRestController;

@SpringBootTest
class BookstorepalinaleksiApplicationTests {

	@Autowired 
	private BookController bController;

	@Autowired 
	private BookRestController bsController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bController).isNotNull();

		assertThat(bsController).isNotNull();
	}



}
