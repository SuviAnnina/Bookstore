package hh.sof003.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof003.bookstore.Domain.Book;
import hh.sof003.bookstore.Domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("Save some books");
			bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "978-0-13-468599-1", 19.90));
			bookRepository.save(new Book("Frankenstein", "Mary Shelley", 1818, "978-1-4398-4092-3", 16.90));
			bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "918-2-4398-9804-6", 21.50));

			log.info("Fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
