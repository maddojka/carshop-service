package service;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class UserServerTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    @DisplayName("Check add user method - user is null")
    public void addUser_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.addUser(null));
    }

    @Test
    @DisplayName("Check add user method - user is OK")
    public void addCar_isNotNull() {
        User user = new User();
        userService.addUser(user);
    }

    @Test
    @DisplayName("Check get user method - user id is negative")
    public void getUser_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.addUser(userService.getUser(-1)));
    }

    @Test
    @DisplayName("Check get user method - user id is oversized")
    public void getUser_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.getUser(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Check get user method - user id is OK")
    public void getUser_correctId() {
        User user = new User();
        userService.getUsers().add(user);
        userService.getUser(0);
    }

    @Test
    @DisplayName("Check edit user method - user id is negative")
    public void editUser_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.editUser(-1, new User()));
    }

    @Test
    @DisplayName("Check edit user method - user id is oversized")
    public void editUser_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.editUser(Integer.MAX_VALUE, new User()));
    }

    @Test
    @DisplayName("Check edit user method - user id is OK")
    public void editUser_correctId() {
        User user =
                new User("user01", "123", "user01@gmail.com", User.Role.CLIENT);
        userService.getUsers().add(user);
        userService.editUser(0, user);
    }

    @Test
    @DisplayName("Check remove user method - user id is negative")
    public void removeUser_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.removeUser(-1));
    }

    @Test
    @DisplayName("Check remove user method - user id is oversized")
    public void removeUser_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.removeUser(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Check remove user method - user id is OK")
    public void removeUser_correctId() {
        User user =
                new User("user01", "123", "user01@gmail.com", User.Role.CLIENT);
        userService.getUsers().add(user);
        userService.removeUser(0);
    }
}
