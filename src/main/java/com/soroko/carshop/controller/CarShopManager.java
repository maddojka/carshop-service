package com.soroko.carshop.controller;

import com.soroko.carshop.service.CarService;
import com.soroko.carshop.service.OrderService;
import com.soroko.carshop.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

import static com.soroko.carshop.constants.Constants.*;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarShopManager {
    private Menu menu;
    private CarService carService;
    private OrderService orderService;
    private UserService userService;
    private final Scanner scanner;
    private boolean isLoggedIn = false;

    public CarShopManager() throws SQLException {
        scanner = new Scanner(System.in);
        carService = new CarService();
        orderService = new OrderService();
        userService = new UserService();
        menu = new Menu(new CarController(carService),
                new OrderController(orderService, userService, carService),
                new UserController(userService));
    }

    /**
     * Launch program
     */
    public void startCarShopLoop() throws SQLException {
        /**
         * Menu selection loop
         */
        System.out.println(WELCOME_MESSAGE);
        while (true) {
            if (!isLoggedIn) {
                menu.printAuthMenu();
                int selector = scanner.nextInt();
                switch (selector) {
                    case 1 -> isLoggedIn = menu.loginOperation(userService);
                    case 2 -> menu.registerOperation(userService);
                }
            }
            if (isLoggedIn) {
                System.out.println(MAIN_MENU);
                if (menu.isClient() || menu.isManager() || menu.isAdmin()) {
                    System.out.println("1. " + CARS_NAVIGATION);
                }
                if (menu.isAdmin() || menu.isManager()) {
                    System.out.println("2. " + ORDERS_NAVIGATION);
                }
                if (menu.isAdmin()) {
                    System.out.println("3. " + USERS_NAVIGATION);
                }
                if (menu.isAdmin()) {
                    System.out.println("4. " + LOGGER_NAVIGATION);
                }
                System.out.println("5. " + EXIT);
                int adminSelector = scanner.nextInt();

                switch (adminSelector) {
                    case 1 -> menu.printCarsMenu(carService);
                    case 2 -> menu.printOrdersMenu(orderService, userService, carService);
                    case 3 -> menu.printUsersMenu(userService);
                    case 4 -> menu.printLoggerMenu();
                    case 5 -> {
                        isLoggedIn = false;
                        menu.setAdmin(false);
                        menu.setManager(false);
                        menu.setClient(false);
                    }
                }
            }
        }
    }
}


