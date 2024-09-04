package com.soroko.carshop.repository;

import com.soroko.carshop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine");
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    private User user;


    @BeforeEach
    void setUp() throws SQLException {
        orderRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        user.setId(1);
        user.setUsername("user999");
        user.setPassword("123");
        user.setEmail("user999@mail.com");
        user.setRole(User.Role.CLIENT);
        userRepository.save(user);
    }


    @Test
    @DisplayName("Checking get all users method - shouldn't be empty")
    void test_shouldFindAll() throws SQLException {
        assertFalse(userRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking user insertion method - should insert user")
    void test_shouldSaveUser() throws SQLException {
        User retreivedUser = userRepository.getById(user.getId());
        Assertions.assertEquals(user, retreivedUser);
    }

    @Test
    @DisplayName("Checking user updating - should update user")
    void test_shouldUpdateUser() throws SQLException {
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setUsername("user999");
        updatedUser.setPassword("123");
        updatedUser.setEmail("user999@mail.com");
        updatedUser.setRole(User.Role.CLIENT);
        userRepository.save(updatedUser);
        User retrievedUser = userRepository.findAll().get(0);
        Assertions.assertEquals(updatedUser, retrievedUser);
    }

    @Test
    @DisplayName("Checking user deleting - should delete user")
    void test_shouldDeleteUserById() throws SQLException {
        User retrievedUser = userRepository.findAll().get(0);
        userRepository.deleteById(retrievedUser.getId());
        Assertions.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking user finding - should find user by car id")
    void test_shouldFindOrderById() throws SQLException {
        User retrievedUser = userRepository.getById(1);
        Assertions.assertNotNull(retrievedUser);
    }
}