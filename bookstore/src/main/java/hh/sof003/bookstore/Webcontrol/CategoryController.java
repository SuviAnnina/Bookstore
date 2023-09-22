package hh.sof003.bookstore.Webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof003.bookstore.Domain.Category;
import hh.sof003.bookstore.Domain.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    /* Listaa kaikki kategoriat */
    @GetMapping("/categorylist")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    /* Lisää uusi kategoria */
    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    /* Tallentaa uuden kategorian */
    @PostMapping("/saveCategory")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }

}
