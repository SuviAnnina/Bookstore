package hh.sof003.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import hh.sof003.bookstore.Domain.User;
import hh.sof003.bookstore.Domain.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    /* Testataan että uusi käyttäjä saadaan lisättyä */
    @Test
    public void createNewUserTest() {
        User user = new User("testUser", "testPassword123", "USER", "test@test.fi");
        userRepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    /* Testataan että käyttäjä saadaan poistettua */
    @Test
    public void deleteUserTest() {
        User user = new User("testUser", "testPassword123", "USER", "test@test.fi");
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        boolean userExists = userRepository.existsById(user.getId());
        assertThat(userExists).isFalse();
    }

    /* Testataan että käyttäjää voidaan etsiä nimellä */
    @Test
    public void searchUserByUserNameTest() {
        User user = new User("testUser", "testPassword123", "USER", "test@test.fi");
        userRepository.save(user);
        User foundUser = userRepository.findByUsername("testUser");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testUser");
    }

}
