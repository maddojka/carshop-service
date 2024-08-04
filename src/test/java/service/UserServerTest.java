package service;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    public void addUser_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.addUser(null));
    }

    @Test
    public void addCar_isNotNull() {
        User user = new User();
        userService.addUser(user);
    }

    @Test
    public void getUser_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.addUser(userService.getUser(-1)));
    }

    @Test
    public void getUser_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.getUser(Integer.MAX_VALUE));
    }

    @Test
    public void getUser_correctId() {
        User user = new User();
        userService.getUsers().add(user);
        userService.getUser(0);
    }

    @Test
    public void editUser_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.editUser(-1, new User()));
    }

    @Test
    public void editUser_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.editUser(Integer.MAX_VALUE, new User()));
    }

    @Test
    public void editUser_correctId() {
        User user =
                new User("user01", "123", "user01@gmail.com", User.Role.CLIENT);
        userService.getUsers().add(user);
        userService.editUser(0, user);
    }

    @Test
    public void removeUser_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.removeUser(-1));
    }

    @Test
    public void removeUser_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.removeUser(Integer.MAX_VALUE));
    }

    @Test
    public void removeUser_correctId() {
        User user =
                new User("user01", "123", "user01@gmail.com", User.Role.CLIENT);
        userService.getUsers().add(user);
        userService.removeUser(0);
    }

}
