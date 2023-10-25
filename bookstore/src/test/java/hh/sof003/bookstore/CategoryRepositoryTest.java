package hh.sof003.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import hh.sof003.bookstore.Domain.Category;
import hh.sof003.bookstore.Domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    /* Testataan että uusi kategoria saadaan lisättyä */
    @Test
    public void createNewCategoryTest() {
        Category category = new Category("CategoryTest");
        categoryRepository.save(category);
        assertThat(category.getCategoryId()).isNotNull();
    }

    /* Testataan että kategoria saadaan poistettua */
    @Test
    public void deleteCategoryTest() {
        Category category = new Category("CategoryTest");
        categoryRepository.save(category);
        categoryRepository.deleteById(category.getCategoryId());
        boolean categoryExists = categoryRepository.existsById(category.getCategoryId());
        assertThat(categoryExists).isFalse();
    }

    /* Testataan että kategoriaa voidaan etsiä nimellä */
    @Test
    public void searchCategoryByNameTest() {
        Category category = new Category("CategoryTest");
        categoryRepository.save(category);
        List<Category> categories = categoryRepository.findByName("CategoryTest");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("CategoryTest");
    }

}
