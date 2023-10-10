package hh.sof003.bookstore.Webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.sof003.bookstore.Domain.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /* Listaa kaikki käyttäjät */
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
}
