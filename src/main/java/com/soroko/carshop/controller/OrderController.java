package com.soroko.carshop.controller;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.CarService;
import com.soroko.carshop.service.OrderService;
import com.soroko.carshop.service.UserService;

import java.time.LocalDate;
import java.util.Scanner;

import static com.soroko.carshop.entity.Order.Status.*;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class OrderController {

    private final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService;
    private final UserService userService;
    private final CarService carService;

    public OrderController(OrderService orderService, UserService userService, CarService carService) {
        this.orderService = orderService;
        this.userService = userService;
        this.carService = carService;
    }

    /**
     * This method perform a pause function in order to see result of the query
     */
    public void pauseBeforeExit() {
        System.out.println("Enter something to return");
        String selector = scanner.next();
    }

    /**
     * This method is an addition layer with scanner functionality
     * receive all available orders
     */
    public void getOrders() {
        if (orderService.getOrders().isEmpty()) {
            System.out.println("No orders found");
        } else System.out.println(orderService.getOrders());
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter orders by car model
     */
    public void getOrderByCarModel() {
        System.out.println("Enter car model");
        String model = scanner.next();
        orderService.findByCarModel(model);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter orders by user
     */
    public void getOrderByUser() {
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
            return;
        } else System.out.println(userService.getUsers());
        System.out.println("Enter user id");
        int id = scanner.nextInt();
        User user = userService.getUser(id);
        orderService.findBy(user);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter orders by status
     */
    public void getOrderByStatus() {
        System.out.println("Enter status");
        String status = scanner.next();
        orderService.findBy(status);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter orders by date
     */
    public void getOrderByDate() {
        System.out.println("Enter date");
        LocalDate localDate = LocalDate.parse(scanner.next());
        orderService.findBy(localDate);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * create new order in the system
     */
    public void createOrder() {
        if (carService.getCars().isEmpty()) {
            System.out.println("No cars found");
            return;
        } else System.out.println(carService.getCars());
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
            return;
        } else System.out.println(userService.getUsers());
        System.out.println("Enter car id");
        int id = scanner.nextInt();
        System.out.println("Enter user id");
        int userId = scanner.nextInt();
        Car car = carService.getCar(id);
        User user = userService.getUser(userId);
        Order order = new Order(user, car, CREATED);
        orderService.addOrder(order);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * edit existing order in the system
     */
    public void editOrder() {
        if (carService.getCars().isEmpty()) {
            System.out.println("No cars found");
            return;
        } else System.out.println(carService.getCars());
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
            return;
        }
        System.out.println("Enter the id of the order you would like to edit");
        int id = scanner.nextInt();
        System.out.println("Enter car id");
        int carId = scanner.nextInt();
        System.out.println("Enter user id");
        int userId = scanner.nextInt();
        System.out.println("Enter the status of the order you would like to edit");
        String status = scanner.next();

        Car car = carService.getCar(carId);
        User user = userService.getUser(userId);
        Order order = orderService.getOrder(id);
        order.setCar(car);
        order.setUser(user);
        if (status.equalsIgnoreCase("created")) {
            order.setStatus(CREATED);
        } else if (status.equalsIgnoreCase("in_progress")) {
            order.setStatus(IN_PROGRESS);
        } else if (status.equalsIgnoreCase("completed")) {
            order.setStatus(COMPLETED);
        }
        orderService.editOrder(carId, order);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * cancel existing order in the system
     */
    public void cancelOrder() {
        if (orderService.getOrders().isEmpty()) {
            System.out.println("No orders found");
            return;
        } else System.out.println(orderService.getOrders());
        System.out.println("Enter the id of the order you want to cancel");
        int id = scanner.nextInt();
        orderService.cancelOrder(id);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * complete existing order in the system
     */
    public void completeOrder() {
        if (orderService.getOrders().isEmpty()) {
            System.out.println("No orders found");
            return;
        } else System.out.println(orderService.getOrders());
        System.out.println("Enter the id of the order you want to complete");
        int id = scanner.nextInt();
        orderService.completeOrder(id);
        pauseBeforeExit();
    }
}
