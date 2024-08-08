package entity;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.soroko.carshop.entity.User.Role.MANAGER;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class UserTest {

    @Test
    @DisplayName("Check empty username exception")
    public void User_empty_username() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("", "456", "user02@gmail.com", MANAGER);
        });
    }

    @Test
    @DisplayName("Check empty password exception")
    public void User_empty_password() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "", "user02@gmail.com", MANAGER);
        });
    }

    @Test
    @DisplayName("Check empty email exception")
    public void User_empty_email() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "123", "", MANAGER);
        });
    }

    @Test
    @DisplayName("Check null username exception")
    public void User_null_username() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(null, "123", "user02@gmail.com", MANAGER);
        });
    }

    @Test
    @DisplayName("Check null password exception")
    public void User_null_password() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", null, "user02@gmail.com", MANAGER);
        });
    }

    @Test
    @DisplayName("Check null email exception")
    public void User_null_email() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "123", null, MANAGER);
        });
    }

    @Test
    @DisplayName("Check null role exception")
    public void User_null_role() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "123", "user02@gmail.com", null);
        });
    }

    @Test
    @DisplayName("Check negative number of purchases exception")
    public void Order_numberOfPurchases_isNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(null, "123", "user02@gmail.com", MANAGER);
            user.setNumberOfpurchases(-1);
        });
    }

    @Test
    @DisplayName("Check user is OK")
    public void User_isOk() {
        User user = new User("admin", "123", "user02@gmail.com", MANAGER);
        user.setRole(MANAGER);
        user.getRole();
        user.setPassword("123");
        user.getPassword();
        user.setEmail("admin@gmail.com");
        user.getEmail();
        user.setUsername("user01");
        user.getUsername();
        user.toString();
        User user1 = new User("admin", "123", "user02@gmail.com", MANAGER);
        user.equals(user1);
        user.hashCode();
    }
}
