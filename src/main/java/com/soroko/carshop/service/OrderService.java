package com.soroko.carshop.service;

import com.soroko.auditstarter.annotations.EnableLoggable;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static com.soroko.carshop.entity.User.Role.CLIENT;


/**
 *  This class consists logic of orders data
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@EnableLoggable
@Service
public class OrderService {
    /**
     * inject user repository to put and get data from database
     */
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Add order to collection
     *
     * @param order - order to add
     */
    public void addOrder(Order order) throws SQLException {
        if (order == null) {
            log.error("Order cannot be null");
            throw new IllegalArgumentException("Order cannot be null");
        }
        orderRepository.save(order);
        log.info("Order added");
    }

    /**
     * Get order by id
     *
     * @param id - id of the order to get
     * @return order - requested Order
     */
    public Order getOrder(int id) throws SQLException {
        if (id < 0) {
            log.error("Invalid order id");
            throw new IllegalArgumentException("Invalid order id");
        }
        return orderRepository.getById(id);
    }

    /**
     * Get all orders from collection
     *
     * @return List of the available orders
     */
    public List<Order> getOrders() throws SQLException {
        return orderRepository.findAll();
    }

    /**
     * Edit order by id
     *
     * @param order - form of the order to edit
     */
    public void editOrder(Order order) throws SQLException {
        if (order == null) {
            log.error("Order is null");
            throw new IllegalArgumentException("Order is null");
        }
        Order orderToEdit = orderRepository.getById(order.getId());
        orderToEdit.setId(order.getId());
        orderToEdit.setCar(order.getCar());
        orderToEdit.setUser(order.getUser());
        orderToEdit.setStatus(order.getStatus());
        orderToEdit.setCreatedAt(order.getCreatedAt());
        orderRepository.save(orderToEdit);
        log.info("Order edited");
    }

    /**
     * Cancel order by id
     *
     * @param id - id of the order to remove from the collection
     */
    public void cancelOrder(int id) throws SQLException {
        if (id < 0) {
            log.error("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        orderRepository.deleteById(id);
        log.info("Order cancelled");
    }

    /**
     * Complete order by id
     *
     * @param id - id of the order to complete the order
     */
    public void completeOrder(int id) throws SQLException {
        if (id < 0) {
            log.error("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        Order order = orderRepository.getById(id);
        order.setStatus(Order.Status.COMPLETED);
        orderRepository.save(order);
    }

    /**
     * Find order by car model
     *
     * @param model - filter by model which was requested
     * @return List of the filtered orders
     */
    public List<Order> findByCarModel(String model) throws SQLException {
        if (model == null || model.isEmpty()) {
            log.error("Car is empty");
            throw new IllegalArgumentException("Car is empty");
        }
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(x -> x.getCar().getModel().equals(model)).toList();
    }

    /**
     * Find order by user
     *
     * @param user - filter by user which was requested
     * @return List of the filtered orders
     */
    public List<Order> findBy(User user) throws SQLException {
        if (user == null) {
            log.error("User is empty");
            throw new IllegalArgumentException("User is empty");
        }
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(x -> x.getUser().getRole().equals(CLIENT)).toList();
    }

    /**
     * Find order by status
     *
     * @param status - filter by status which was requested
     * @return List of the filtered orders
     */
    public List<Order> findBy(String status) throws SQLException {
        if ("".equals(status) || status == null) {
            log.error("Order is empty");
            throw new IllegalArgumentException("Order is empty");
        }
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(x -> x.getStatus().toString().equals(status))
                .sorted(Comparator.comparing(Order::getId))
                .toList();
    }

    /**
     * Find order by date
     *
     * @param localDate - filter by date which was requested
     * @return List of the filtered orders
     */
    public List<Order> findBy(LocalDate localDate) throws SQLException {
        if (localDate == null) {
            log.error("LocalDate is empty");
            throw new IllegalArgumentException("LocalDate is empty");
        }
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(x -> x.getCreatedAt().isEqual(localDate)).toList();
    }
}
