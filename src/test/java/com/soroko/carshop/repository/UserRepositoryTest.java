package com.soroko.carshop.repository;

import com.soroko.carshop.entity.User;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
class UserRepositoryTest {

    private PostgresTest postgresTest;
    private CarRepository carRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private User user;


    @BeforeEach
    void setUp() throws SQLException {
        postgresTest = new PostgresTest();
        carRepository = new CarRepository();
        orderRepository = new OrderRepository();
        userRepository = new UserRepository();
        orderRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        User user = new User("user999", "123", "user999@mail.com",
                User.Role.CLIENT);
        userRepository.insert(user);
    }


    @Test
    @DisplayName("Checking get all users method - shouldn't be empty")
    void test_shouldGetAllUsers() throws SQLException {
        assertFalse(userRepository.getAllUsers().isEmpty());
    }

    @Test
    @DisplayName("Checking user insertion method - should insert user")
    void test_shouldInsertUser() throws SQLException {
        User retreivedUser = userRepository.findById(user.getId());
        Assertions.assertEquals(user, retreivedUser);
    }

    @Test
    @DisplayName("Checking user updating - should update user")
    void test_shouldUpdateUser() throws SQLException {
        User updatedUser = new User("user999", "123", "user999@mail.com",
                User.Role.MANAGER);
        userRepository.insert(user);
        userRepository.update(updatedUser);
        User retrievedUser = userRepository.getAllUsers().get(0);
        Assertions.assertEquals(updatedUser, retrievedUser);
    }

    @Test
    @DisplayName("Checking user deleting - should delete user")
    void test_shouldDeleteUserById() throws SQLException {
        User retrievedUser = userRepository.getAllUsers().get(0);
        userRepository.deleteById(retrievedUser.getId());
        assertTrue(userRepository.getAllUsers().isEmpty());
    }

    @Test
    @DisplayName("Checking user finding - should find user by car id")
    void test_shouldFindOrderById() throws SQLException {
        User retrievedUser = userRepository.findById(1);
        Assertions.assertTrue(retrievedUser != null);
    }
}