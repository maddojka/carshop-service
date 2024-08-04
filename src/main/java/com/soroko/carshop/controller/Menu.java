package com.soroko.carshop.controller;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.logger.CarShopLogger;
import com.soroko.carshop.service.CarService;
import com.soroko.carshop.service.OrderService;
import com.soroko.carshop.service.UserService;

import java.util.Scanner;

import static com.soroko.carshop.constants.Constants.*;
import static com.soroko.carshop.entity.User.Role.CLIENT;
import static com.soroko.carshop.logger.CarShopLogger.LOGGER;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class Menu {
    private boolean isClient;
    private boolean isManager;
    private boolean isAdmin;
    private final CarController carController = new CarController();
    private final OrderController orderController = new OrderController();
    private final UserController userController = new UserController();
    private final CarShopLogger carShopLogger = new CarShopLogger();
    private final Scanner sc = new Scanner(System.in);

    /**
     * Print auth menu
     */
    public void printAuthMenu() {
        System.out.println(SELECT);
        System.out.println("1. " + SIGN_IN);
        System.out.println("2. " + ADD_USER);
    }

    /**
     * Register new user
     */
    public void registerOperation(UserService userService) {
        if (userService == null || userService.getUsers().isEmpty()) {
            LOGGER.severe("userService is null or empty");
            throw new IllegalArgumentException("userService is null or empty");
        }
        System.out.println(LOGIN);
        String login = sc.next();
        System.out.println(PASSWORD);
        String password = sc.next();
        System.out.println(EMAIL);
        String email = sc.next();
        User user = new User(login, password, email, CLIENT);
        if (userService.getUsers().contains(user)) {
            System.out.println("This user is already registered");
            LOGGER.info("This user is already registered");
        } else {
            userService.addUser(user);
            System.out.println("Registered Successfully");
            LOGGER.info("Registered Successfully");
        }
    }

    /**
     * User login process
     */
    public boolean loginOperation(UserService userService) {
        if (userService == null || userService.getUsers().isEmpty()) {
            LOGGER.severe("userService is null or empty");
            throw new IllegalArgumentException("userService is null or empty");
        }
        System.out.println(LOGIN);
        String login = sc.next();
        System.out.println(PASSWORD);
        String password = sc.next();
        User temp = userService.getUsers().stream()
                .filter(x -> x.getUsername().equals(login))
                .filter(x -> x.getPassword().equals(password)).findAny().orElse(null);
        if (temp != null) {
            if (login.equals("admin")) isAdmin = true;
            else if (login.equals("manager")) isManager = true;
            else isClient = true;
            System.out.println("Login successful");
            LOGGER.info("Login successful");
            return true;
        }
        System.out.println("Login failed");
        LOGGER.info("Login failed");
        return false;
    }

    /**
     * Cars service selection
     */
    public void printCarsMenu(CarService carService) {
        if (carService == null) {
            LOGGER.severe("CarService is null");
            throw new IllegalArgumentException("carService is null");
        }
        while (true) {
            System.out.println(CARS_NAVIGATION);
            System.out.println("1." + GET_CARS);
            System.out.println("2." + GET_CAR_BY_MAKE);
            System.out.println("3." + GET_CAR_BY_MODEL);
            System.out.println("4." + GET_CAR_BY_YEAR);
            System.out.println("5." + GET_CAR_BY_PRICE);
            System.out.println("6." + GET_CAR_BY_CONDITION);
            System.out.println("7. " + GET_CAR_BY_CONDITION_AND_PRICE);
            System.out.println("8. " + RETURN_MENU);
            if (isAdmin || isManager) {
                System.out.println("9. " + ADD_CAR);
                System.out.println("10. " + EDIT_CAR);
                System.out.println("11. " + SELL_CAR);
            }
            int selector = sc.nextInt();
            switch (selector) {
                case 1 -> carController.getCars(carService);
                case 2 -> carController.getCarByMake(carService);
                case 3 -> carController.getCarByModel(carService);
                case 4 -> carController.getCarByYear(carService);
                case 5 -> carController.getCarByPrice(carService);
                case 6 -> carController.getCarByCondition(carService);
                case 7 -> carController.getCarByConditionAndPrice(carService);
                case 8 -> {
                    return;
                }
                case 9 -> carController.registerCar(carService);
                case 10 -> carController.editCar(carService);
                case 11 -> carController.removeCar(carService);
            }
        }
    }

    /**
     * Order service selection
     */
    public void printOrdersMenu(OrderService orderService,
                                UserService userService,
                                CarService carService) {
        if (orderService == null || userService == null || carService == null) {
            LOGGER.info("Service is null");
            throw new IllegalArgumentException("Service is null");
        }
        while (true) {
            if (isAdmin || isManager) {
                System.out.println(ORDERS_NAVIGATION);
                System.out.println("1. " + GET_ORDERS);
                System.out.println("2. " + GET_ORDER_BY_CAR);
                System.out.println("3. " + GET_ORDER_BY_USER);
                System.out.println("4. " + GET_ORDER_BY_STATUS);
                System.out.println("5. " + GET_ORDER_BY_DATE);
                System.out.println("6. " + ADD_ORDER);
                System.out.println("7. " + UPDATE_ORDER);
                System.out.println("8. " + CANCEL_ORDER);
                System.out.println("9. " + COMPLETE_ORDER);
                System.out.println("10. " + RETURN_MENU);
            }
            int selector = sc.nextInt();
            switch (selector) {
                case 1 -> orderController.getOrders(orderService);
                case 2 -> orderController.getOrderByCarModel(orderService);
                case 3 -> orderController.getOrderByUser(orderService, userService);
                case 4 -> orderController.getOrderByStatus(orderService);
                case 5 -> orderController.getOrderByDate(orderService);
                case 6 -> orderController.createOrder(orderService, userService, carService);
                case 7 -> orderController.editOrder(orderService, userService, carService);
                case 8 -> orderController.cancelOrder(orderService);
                case 9 -> orderController.completeOrder(orderService);
                case 10 -> {
                    return;
                }
            }
        }
    }

    /**
     * User service selection
     */
    public void printUsersMenu(UserService userService) {
        if (userService == null) {
            LOGGER.info("orderService is null");
            throw new IllegalArgumentException("orderService is null");
        }
        while (true) {
            if (isAdmin) {
                System.out.println(USERS_NAVIGATION);
                System.out.println("1. " + GET_USERS);
                System.out.println("2. " + ADD_USER);
                System.out.println("3. " + EDIT_USER);
                System.out.println("4. " + REMOVE_USER);
                System.out.println("5. " + RETURN_MENU);
            }
            int selector = sc.nextInt();
            switch (selector) {
                case 1 -> userController.getUsers(userService);
                case 2 -> userController.registerUser(userService);
                case 3 -> userController.editUser(userService);
                case 4 -> userController.removeUser(userService);
                case 5 -> {
                    return;
                }
            }
        }
    }

    /**
     * Logger selection
     */
    public void printLoggerMenu() {
        while (true) {
            if (isAdmin) {
                System.out.println("1. " + TO_FILE);
                System.out.println("2. " + TO_CONSOLE);
                System.out.println("3. " + RETURN_MENU);
            }
            int selector = sc.nextInt();
            switch (selector) {
                case 1 -> carShopLogger.setFileHandler();
                case 2 -> carShopLogger.setConsoleHandler();
                case 3 -> {
                    return;
                }
            }
        }
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

