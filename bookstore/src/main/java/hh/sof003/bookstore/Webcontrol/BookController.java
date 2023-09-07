package hh.sof003.bookstore.Webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class BookController {

    @GetMapping("/index")
    public String listBooks(Model model) {

        return "bookstore";
    }
}
