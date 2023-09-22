package hh.sof003.bookstore.Webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import hh.sof003.bookstore.Domain.Book;
import hh.sof003.bookstore.Domain.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    /* Listaa kaikki tietokannasta löytyvät kirjat */
    @GetMapping("/booklist")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    /* Poistaa valitun kirjan id:n perusteella tietokannasta */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

    /* Valitaan muokattava kirja tietokannasta id:n perusteella */
    @GetMapping("/editbook/{id}")
    public String editBook(@PathVariable("id") long bookId, Model model) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            model.addAttribute("book", book);
        }
        return "editbook";
    }

    /* Lisää uuden kirjan */
    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    /* Tallentaa uuden kirjan tietokantaan */
    @PostMapping("/save")
    public String saveBook(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    /* Päivittää muokatun kirjan tiedot vanhojen tietojen tilalle tietokantaan */
    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book updatedBook) {
        Long bookId = updatedBook.getId();
        Book currentBook = bookRepository.findById(bookId).orElse(null);

        if (currentBook != null) {
            currentBook.setTitle(updatedBook.getTitle());
            currentBook.setAuthor(updatedBook.getAuthor());
            currentBook.setYear(updatedBook.getYear());
            currentBook.setIsbn(updatedBook.getIsbn());
            currentBook.setPrice(updatedBook.getPrice());

            bookRepository.save(currentBook);
        }
        return "redirect:/booklist";
    }

}
