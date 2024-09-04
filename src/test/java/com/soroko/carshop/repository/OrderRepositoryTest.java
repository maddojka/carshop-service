package com.soroko.carshop.repository;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.entity.Order;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class OrderRepositoryTest {
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
    private Car car;
    private Order order;


    @BeforeEach
    void setUp() throws SQLException {
        orderRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        user = new User();
        user.setId(1);
        user.setUsername("user999");
        user.setPassword("123");
        user.setEmail("user999@mail.com");
        user.setRole(User.Role.CLIENT);
        car = new Car("Volksvagen", "Polo", 2020, 2_000_000.0, "used");
        order = new Order();
        order.setUser(user);
        order.setCar(car);
        order.setStatus(Order.Status.CREATED);
        orderRepository.save(order);
    }


    @Test
    @DisplayName("Checking get all orders method - shouldn't be empty")
    void test_shouldFindAll() throws SQLException {
        assertFalse(orderRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking order insertion method - should insert order")
    void test_shouldSaveOrder() throws SQLException {
        Order retrievedOrder = orderRepository.getById(order.getId());
        Assertions.assertEquals(order, retrievedOrder);
    }

    @Test
    @DisplayName("Checking order updating - should update order")
    void test_shouldUpdateOrder() throws SQLException {
        Order updatedOrder = new Order();
        updatedOrder.setUser(user);
        updatedOrder.setCar(car);
        updatedOrder.setStatus(Order.Status.IN_PROGRESS);
        orderRepository.save(updatedOrder);
        Order retrievedOrder = orderRepository.findAll().get(0);
        Assertions.assertEquals(updatedOrder, retrievedOrder);
    }

    @Test
    @DisplayName("Checking order deleting - should delete order")
    void test_shouldDeleteOrderById() throws SQLException {
        Order retrievedOrder = orderRepository.findAll().get(0);
        orderRepository.deleteById(retrievedOrder.getId());
        Assertions.assertTrue(orderRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking order finding - should find order by car id")
    void test_shouldFindOrderById() throws SQLException {
        Order retrievedOrder = orderRepository.getById(1);
        Assertions.assertNotNull(retrievedOrder);
    }
}