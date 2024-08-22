package com.soroko.carshop.repository;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
class OrderRepositoryTest {

    private PostgresTest postgresTest;
    private CarRepository carRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private User user;
    private Car car;
    private Order order;


    @BeforeEach
    void setUp() throws SQLException, IOException {
        postgresTest = new PostgresTest();
        carRepository = new CarRepository();
        orderRepository = new OrderRepository();
        userRepository = new UserRepository();
        orderRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        user = new User("user999", "123", "user999@mail.com", User.Role.CLIENT);
        car = new Car("Volksvagen", "Polo", 2020, 2_000_000.0, "used");
        user.setId(1);
        car.setId(1);
        order =  new Order(user, car, Order.Status.CREATED);
        orderRepository.insert(order);
    }


    @Test
    @DisplayName("Checking get all orders method - shouldn't be empty")
    void test_shouldGetAllOrders() throws SQLException {
        assertFalse(orderRepository.getAllOrders().isEmpty());
    }

    @Test
    @DisplayName("Checking order insertion method - should insert order")
    void test_shouldInsertOrder() throws SQLException {
        Order retrievedOrder = orderRepository.findById(order.getId());
        Assertions.assertEquals(order, retrievedOrder);
    }

    @Test
    @DisplayName("Checking order updating - should update order")
    void test_shouldUpdateOrder() throws SQLException {
        Order updatedOrder = new Order(user, car,
                Order.Status.IN_PROGRESS);
        orderRepository.update(updatedOrder);
        Order retrievedOrder = orderRepository.getAllOrders().get(0);
        Assertions.assertEquals(updatedOrder, retrievedOrder);
    }

    @Test
    @DisplayName("Checking order deleting - should delete order")
    void test_shouldDeleteOrderById() throws SQLException {
        Order retrievedOrder = orderRepository.getAllOrders().get(0);
        orderRepository.deleteById(retrievedOrder.getId());
        assertTrue(orderRepository.getAllOrders().isEmpty());
    }

    @Test
    @DisplayName("Checking order finding - should find order by car id")
    void test_shouldFindOrderById() throws SQLException {
        Order retrievedOrder = orderRepository.findById(1);
        Assertions.assertTrue(retrievedOrder != null);
    }
}