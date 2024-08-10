package com.soroko.carshop.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class OrderTest {

    @Test
    @DisplayName("Check null user exception")
    public void Order_null_User() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(null, new Car(), Order.Status.IN_PROGRESS);
        });

    }

    @Test
    @DisplayName("Check null car exception")
    public void Order_null_Car() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(new User(), null, Order.Status.IN_PROGRESS);
        });
    }

    @Test
    @DisplayName("Check null status exception")
    public void Order_null_Status() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(new User(), new Car(), null);
        });
    }

    @Test
    @DisplayName("Check order is OK")
    public void Order_is_Ok() {
        Order order = new Order(new User(), new Car(), Order.Status.IN_PROGRESS);
        order.setStatus(Order.Status.COMPLETED);
        order.getStatus();
        order.setId(2);
        order.getId();
        order.setUser(new User());
        order.getUser();
        order.setCar(new Car());
        order.getCar();
        order.toString();
    }
}
