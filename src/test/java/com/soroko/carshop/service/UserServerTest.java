package com.soroko.carshop.service;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class UserServerTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Check add user method - user is null")
    public void addUser_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.addUser(null));
    }

    @Test
    @DisplayName("Check add user method - user is OK")
    public void addCar_isNotNull() throws SQLException {
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
    public void getUser_correctId() throws SQLException {
        User user = new User();
        userService.getUsers().add(user);
        userService.getUser(0);
    }

    @Test
    @DisplayName("Check edit user method - user id is negative")
    public void editUser_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.editUser(new User()));
    }

    @Test
    @DisplayName("Check edit user method - user id is oversized")
    public void editUser_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.editUser(new User()));
    }

    @Test
    @DisplayName("Check edit user method - user id is OK")
    public void editUser_correctId() throws SQLException {
        User user =
                new User("user01", "123", "user01@gmail.com", User.Role.CLIENT);
        userService.getUsers().add(user);
        userService.editUser(user);
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
    public void removeUser_correctId() throws SQLException {
        User user =
                new User("user01", "123", "user01@gmail.com", User.Role.CLIENT);
        userService.getUsers().add(user);
        userService.removeUser(0);
    }
}
