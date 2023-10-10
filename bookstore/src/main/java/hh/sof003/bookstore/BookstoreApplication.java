package hh.sof003.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof003.bookstore.Domain.Book;
import hh.sof003.bookstore.Domain.BookRepository;
import hh.sof003.bookstore.Domain.Category;
import hh.sof003.bookstore.Domain.CategoryRepository;
import hh.sof003.bookstore.Domain.User;
import hh.sof003.bookstore.Domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository,
			UserRepository userRepository) {
		return (args) -> {
			log.info("Save some categories");
			Category category1 = new Category("Drama");
			categoryRepository.save(category1);
			Category category2 = new Category("Romance");
			categoryRepository.save(category2);
			Category category3 = new Category("Horror");
			categoryRepository.save(category3);
			Category category4 = new Category("Thriller");
			categoryRepository.save(category4);
			Category category5 = new Category("Sci-fi");
			categoryRepository.save(category5);

			log.info("Fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			log.info("Save some books");
			bookRepository
					.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "978-0-13-468599-1", 19.90, category2));
			bookRepository.save(new Book("Frankenstein", "Mary Shelley", 1818, "978-1-4398-4092-3", 16.90, category3));
			bookRepository
					.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "918-2-4398-9804-6", 21.50, category4));
			bookRepository
					.save(new Book("The Crucible", "Arthur Miller", 1953, "574-60-05-45484-54", 15.60, category1));

			log.info("Fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

			log.info("Save some users");

			// Hertta / queen0Fhearts
			User user1 = new User("Hertta", "$2a$10$uZyuzJuxKK394drd2JxS1ubkfBvAQdlGhEEoVUTzcgkJxEYXWUbSC", "USER",
					"hertta@hertta.com");

			// Tessa / mlemTILLn0END
			User user2 = new User("Tessa", "$2a$10$5mKSQ1Zq8PxA2S9l9PcjaOzWcojaM47XLmZtzVBEZOPFLfHACE9.e", "USER",
					"tessa@tessa.fi");

			// Admin / adminsGONNA_adm1N
			User user3 = new User("Admin", "$2a$10$4CqqaxPe5SRF6K//Qq4zP.vsa3DS4T/VYX9rzWPq0yg2y7qsjbEOu", "ADMIN",
					"admin@admin.net");

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);

			log.info("we have users!");

		};
	}

}
