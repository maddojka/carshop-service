package com.soroko.carshop.service;

import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.soroko.carshop.entity.User.Role.CLIENT;
import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 */
public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    /**
     * Add order to collection
     */
    public void addOrder(Order order) {
        if (orders.contains(orders)) {
            LOGGER.warning("Order already exists");
            return;
        }
        if (order == null) {
            LOGGER.severe("Order cannot be null");
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.orders.add(order);
        LOGGER.info("Order added");
    }

    /**
     * Get order by id
     */
    public Order getOrder(int id) {
        if (id < 0 || id >= this.orders.size()) {
            LOGGER.severe("Invalid order id");
            throw new IllegalArgumentException("Invalid order id");
        }
        return this.orders.get(id);
    }

    /**
     * Get all orders from collection
     */
    public List<Order> getOrders() {
        return this.orders;
    }

    /**
     * Edit order by id
     */
    public void editOrder(int id, Order order) {
        if (order == null || id < 0 || id >= this.orders.size()) {
            LOGGER.severe("Invalid order id or order is null");
            throw new IllegalArgumentException("Invalid order id");
        }
        this.orders.get(id).setCar(order.getCar());
        this.orders.get(id).setUser(order.getUser());
        LOGGER.info("Order edited");
    }

    /**
     * Cancel order by id
     */
    public void cancelOrder(int id) {
        if (id < 0 || id >= this.orders.size()) {
            LOGGER.severe("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        this.orders.remove(id);
        LOGGER.info("Order cancelled");
    }

    /**
     * Complete order by id
     */
    public void completeOrder(int id) {
        if (id < 0 || id >= this.orders.size()) {
            LOGGER.severe("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        Order order = this.orders.get(id);
        order.setStatus(Order.Status.COMPLETED);
    }

    /**
     * Find order by car model
     */
    public List<Order> findByCarModel(String carName) {
        if (carName == null || carName.isEmpty()) {
            LOGGER.severe("Car is empty");
            throw new IllegalArgumentException("Car is empty");
        }
        return orders.stream().filter(x -> x.getCar().getModel().equals(carName)).toList();
    }

    /**
     * Find order by user
     */
    public List<Order> findBy(User user) {
        if (user == null) {
            LOGGER.severe("User is empty");
            throw new IllegalArgumentException("User is empty");
        }
        return orders.stream().filter(x -> x.getUser().getRole().equals(CLIENT)).toList();
    }

    /**
     * Find order by status
     */
    public List<Order> findBy(String status) {
        if ("".equals(status) || status == null) {
            LOGGER.severe("Order is empty");
            throw new IllegalArgumentException("Order is empty");
        }
        return orders.stream().filter(x -> x.getStatus().toString().equals(status))
                .sorted(Comparator.comparing(Order::getOrderId))
                .toList();
    }

    /**
     * Find order by date
     */
    public List<Order> findBy(LocalDate localDate) {
        if (localDate == null) {
            LOGGER.severe("LocalDate is empty");
            throw new IllegalArgumentException("LocalDate is empty");
        }
        return orders.stream().filter(x -> x.getCreatedAt().isEqual(localDate)).toList();
    }


}
