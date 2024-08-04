package entity;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.soroko.carshop.entity.User.Role.MANAGER;

/**
 * @author yuriy.soroko
 */
public class UserTest {

    @Test
    public void User_empty_username() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("", "456", "user02@gmail.com", MANAGER);
        });
    }

    @Test
    public void User_empty_password() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "", "user02@gmail.com", MANAGER);
        });
    }

    @Test
    public void User_empty_email() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "123", "", MANAGER);
        });
    }

    @Test
    public void User_null_username() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(null, "123", "user02@gmail.com", MANAGER);
        });
    }

    @Test
    public void User_null_password() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", null, "user02@gmail.com", MANAGER);
        });
    }

    @Test
    public void User_null_email() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "123", null, MANAGER);
        });
    }

    @Test
    public void User_null_role() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User("admin", "123", "user02@gmail.com", null);
        });
    }

    @Test
    public void Order_numberOfPurchases_isNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(null, "123", "user02@gmail.com", MANAGER);
            user.setNumberOfpurchases(-1);
        });
    }

    @Test
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
