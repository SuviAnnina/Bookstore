package hh.sof003.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import hh.sof003.bookstore.Webcontrol.BookController;
import hh.sof003.bookstore.Webcontrol.BookRestController;
import hh.sof003.bookstore.Webcontrol.CategoryController;
import hh.sof003.bookstore.Webcontrol.UserController;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;

	@Autowired
	private BookRestController bookRestController;

	@Autowired
	private CategoryController categoryController;

	@Autowired
	private UserController userController;

	/* Testataan, latautuvatko controlleriluokat */
	@Test
	public void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(bookRestController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
