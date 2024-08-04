package com.soroko.carshop.controller;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.CarService;
import com.soroko.carshop.service.OrderService;
import com.soroko.carshop.service.UserService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;

import static com.soroko.carshop.entity.Order.Status.*;
import static com.soroko.carshop.logger.CarShopLogger.LOGGER;

/**
 * @author yuriy.soroko
 */
public class OrderController {

    private final Scanner scanner = new Scanner(System.in);

    public void pauseBeforeExit() {
        System.out.println("Enter something to return");
        String selector = scanner.next();
    }

    public void getOrders(OrderService orderService) {
        orderServiceIsNull(orderService);
        if (orderService.getOrders().isEmpty()) {
            System.out.println("No orders found");
        } else System.out.println(orderService.getOrders());
        pauseBeforeExit();
    }

    public void getOrderByCarModel(OrderService orderService) {
        orderServiceIsNull(orderService);
        System.out.println("Enter car model");
        String model = scanner.next();
        orderService.findByCarModel(model);
        pauseBeforeExit();
    }

    public void getOrderByUser(OrderService orderService,
                               UserService userService) {
        orderServiceIsNull(orderService);
        UserController.userServiceIsNull(userService);
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

    public void getOrderByStatus(OrderService orderService) {
        orderServiceIsNull(orderService);
        System.out.println("Enter status");
        String status = scanner.next();
        orderService.findBy(status);
        pauseBeforeExit();
    }

    public void getOrderByDate(OrderService orderService) {
        orderServiceIsNull(orderService);
        System.out.println("Enter date");
        LocalDate localDate = LocalDate.parse(scanner.next());
        orderService.findBy(localDate);
        pauseBeforeExit();
    }

    public void createOrder(OrderService orderService,
                            UserService userService,
                            CarService carService) {
        orderServiceIsNull(orderService);
        UserController.userServiceIsNull(userService);
        CarController.carServiceIsNull(carService);
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

    public void editOrder(OrderService orderService,
                          UserService userService,
                          CarService carService) {
        orderServiceIsNull(orderService);
        UserController.userServiceIsNull(userService);
        CarController.carServiceIsNull(carService);
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

    public void cancelOrder(OrderService orderService) {
        orderServiceIsNull(orderService);
        if (orderService.getOrders().isEmpty()) {
            System.out.println("No orders found");
            return;
        } else System.out.println(orderService.getOrders());
        System.out.println("Enter the id of the order you want to cancel");
        int id = scanner.nextInt();
        orderService.cancelOrder(id);
        pauseBeforeExit();
    }

    public void completeOrder(OrderService orderService) {
        orderServiceIsNull(orderService);
        if (orderService.getOrders().isEmpty()) {
            System.out.println("No orders found");
            return;
        } else System.out.println(orderService.getOrders());
        System.out.println("Enter the id of the order you want to complete");
        int id = scanner.nextInt();
        orderService.completeOrder(id);
        pauseBeforeExit();
    }

    public static void orderServiceIsNull(OrderService orderService) {
        if (orderService == null) {
            LOGGER.log(Level.SEVERE, "orderService is null");
            throw new IllegalArgumentException("orderService is null");
        }
    }
}
