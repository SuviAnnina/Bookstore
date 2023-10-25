package hh.sof003.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import hh.sof003.bookstore.Domain.Book;
import hh.sof003.bookstore.Domain.BookRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    /* Testataan että uusi kirja saadaan lisättyä ja tallennettua tietokantaan */
    @Test
    public void createAndSaveNewBookTest() {
        Book book = new Book("Anna Karenina", "Leo Tolstoy");
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    /* Testataan että kirja saadaan poistettua tietokannasta */
    @Test
    public void deleteBookTest() {
        Book book = new Book("Anna Karenina", "Leo Tolstoy");
        bookRepository.save(book);
        bookRepository.deleteById(book.getId());
        boolean bookExists = bookRepository.existsById(book.getId());
        assertThat(bookExists).isFalse();
    }

    /* Testataan että tietokannasta voidaan etsiä kirjaa nimellä */
    @Test
    public void searchBookByTitleTest() {
        Book book = new Book("Anna Karenina", "Leo Tolstoy");
        bookRepository.save(book);
        List<Book> books = bookRepository.findByTitle("Anna Karenina");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Anna Karenina");
    }

    /* Testataan että tietokannasta löydetään kaikki kirjat */
    @Test
    public void findAllBooksTest() {
        Book book1 = new Book("Anna Karenina", "Leo Tolstoy");
        bookRepository.save(book1);
        List<Book> books = (List<Book>) bookRepository.findAll();
        assertThat(books).isNotNull();

        long bookId = book1.getId();
        boolean bookExists = false;

        for (Book book : books) {
            if (book.getId() == (bookId)) {
                bookExists = true;
                break;
            }
        }
        assertThat(bookExists).isTrue();
    }
}