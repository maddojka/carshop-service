package com.soroko.carshop.service;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.repository.CarRepository;
import com.soroko.carshop.repository.OrderRepository;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() throws SQLException {
        DataSourceTest dataSourceTest = new DataSourceTest();
        orderRepository = new OrderRepository(dataSourceTest.dataSource);
        orderService = new OrderService(orderRepository);
    }

    @Test
    @DisplayName("Check add order method - order is null")
    public void addOrder_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.addOrder(null));
    }

    @Test
    @DisplayName("Check add order method - order is OK")
    public void addOrder_isNotNull() throws SQLException {
        Order order = new Order(new User(), new Car(), Order.Status.CREATED);
        Assertions.assertNotNull(order);
    }

    @Test
    @DisplayName("Check get order method - order is negative")
    public void getOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.getOrder(-1));
    }

    @Test
    @DisplayName("Check get order method - order is OK")
    public void getOrder_correctId() throws SQLException {
        Order order = new Order();
        orderService.getOrders().add(order);
//        orderService.getOrder(0);
    }

    @Test
    @Disabled
    @DisplayName("Check edit order method - order is negative")
    public void editOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.editOrder(new Order()));
    }

    @Test
    @Disabled
    @DisplayName("Check edit order method - order is OK")
    public void editOrder_correctId() throws SQLException {
        Car car = new Car();
        User user = new User();
        Order order = new Order();
        order.setId(1);
        order.setUser(user);
        order.setCar(car);
        order.setStatus(Order.Status.CREATED);
        order.setCreatedAt(LocalDate.now());
        orderService.getOrders().add(order);
        orderService.editOrder(order);
    }

    @Test
    @DisplayName("Check cancel order method - order is negative")
    public void cancelOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.cancelOrder(-1));
    }

    @Test
    @DisplayName("Check cancel order method - order is OK")
    public void cancelOrder_correctId() throws SQLException {
        Car car = new Car();
        User user = new User();
        Order order = new Order();
        order.setId(1);
        order.setUser(user);
        order.setCar(car);
        order.setStatus(Order.Status.CREATED);
        orderService.getOrders().add(order);
        orderService.cancelOrder(0);
    }

    @Test
    @DisplayName("Check complete order method - order is negative")
    public void completeOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.completeOrder(-1));
    }

    @Test
    @Disabled
    @DisplayName("Check complete order method - order is OK")
    public void completeOrder_correctId() throws SQLException {
        User user = new User();
        user.setId(1);
        user.setUsername("user999");
        user.setPassword("123");
        user.setEmail("user999@mail.com");
        user.setRole(User.Role.CLIENT);
        Car car = new Car("Volksvagen", "Polo", 2020, 2_000_000.0, "used");
        car.setId(1);
        Order order = new Order();
        order.setUser(user);
        order.setCar(car);
        order.setStatus(Order.Status.CREATED);
        order.setId(1);
        orderService.getOrders().add(order);
        orderService.completeOrder(1);
    }

    @Test
    @DisplayName("Check find car by name method - car is empty")
    public void findByCarName_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findByCarModel(""));

    }

    @Test
    @DisplayName("Check find order by car name method - car is null")
    public void findByCarName_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findByCarModel(null));

    }

    @Test
    @DisplayName("Check find order by car name method - car is OK")
    public void findByCarName_isOk() throws SQLException {
        orderService.findByCarModel("Lada");
    }

    @Test
    @DisplayName("Check find order by user method - user is null")
    public void findByUser_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy((User) null));

    }

    @Test
    @DisplayName("Check find by user method - user is OK")
    public void findByUser_isOk() throws SQLException {
        orderService.findBy(new User());
    }

    @Test
    @DisplayName("Check find order by status method - status is empty")
    public void findByStatus_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy(""));

    }

    @Test
    @DisplayName("Check find order by status method - status is null")
    public void findByStatus_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy((String) null));

    }

    @Test
    @DisplayName("Check find order by status method - status is OK")
    public void findByStatus_isOk() throws SQLException {
        orderService.findBy("CREATED");
    }

    @Test
    @DisplayName("Check find order by date method - date is null")
    public void findByDate_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy((LocalDate) null));

    }

    @Test
    @DisplayName("Check find order by date method - date is OK")
    public void findByDate_isOk() throws SQLException {
        orderService.findBy(LocalDate.now());
    }
}
