package com.soroko.carshop.service;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() throws SQLException {
        orderService = new OrderService();
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
        Order order = new Order();
        orderService.addOrder(order);
    }

    @Test
    @DisplayName("Check get order method - order is negative")
    public void getOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.getOrder(-1));
    }

    @Test
    @DisplayName("Check get order method - order is oversized")
    public void getOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.getOrder(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Check get order method - order is OK")
    public void getOrder_correctId() throws SQLException {
        Order order = new Order();
        orderService.getOrders().add(order);
        orderService.getOrder(0);
    }

    @Test
    @DisplayName("Check edit order method - order is negative")
    public void editOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.editOrder(new Order()));
    }

    @Test
    @DisplayName("Check edit order method - otder is oversized")
    public void editOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.editOrder(new Order()));
    }

    @Test
    @DisplayName("Check edit order method - order is OK")
    public void editOrder_correctId() throws SQLException {
        Car car = new Car();
        User user = new User();
        Order order = new Order(user, car, Order.Status.CREATED);
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
    @DisplayName("Check cancel order method - order is oversized")
    public void cancelOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.cancelOrder(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Check cancel order method - order is OK")
    public void cancelOrder_correctId() throws SQLException {
        Car car = new Car();
        User user = new User();
        Order order = new Order(user, car, Order.Status.CREATED);
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
    @DisplayName("Check complete order method - otder is oversized")
    public void completeOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.completeOrder(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Check complete order method - order is OK")
    public void completeOrder_correctId() throws SQLException {
        Order order = new Order();
        orderService.getOrders().add(order);
        orderService.completeOrder(0);
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
