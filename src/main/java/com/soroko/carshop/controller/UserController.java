package com.soroko.carshop.controller;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.UserService;

import java.util.Scanner;
import java.util.logging.Level;

import static com.soroko.carshop.entity.User.Role.ADMINISTRATOR;
import static com.soroko.carshop.entity.User.Role.MANAGER;
import static com.soroko.carshop.logger.CarShopLogger.LOGGER;

/**
 * @author yuriy.soroko
 */
public class UserController {

    private final Scanner scanner = new Scanner(System.in);

    public void pauseBeforeExit() {
        System.out.println("Enter something to return");
        String selector = scanner.next();
    }

    public void getUsers(UserService userService) {
        userServiceIsNull(userService);
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
        } else System.out.println(userService.getUsers());
        pauseBeforeExit();
    }

    public void registerUser(UserService userService) {
        userServiceIsNull(userService);
        System.out.println("Enter username");
        String username = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();
        System.out.println("Enter email");
        String email = scanner.next();
        System.out.println("Enter number of purchases");
        int purchases = scanner.nextInt();
        System.out.println("Enter role");
        String role = scanner.next();
        User newUser = new User(username, password, email, purchases, User.Role.CLIENT);
        if (role.equalsIgnoreCase(ADMINISTRATOR.toString())) newUser.setRole(ADMINISTRATOR);
        else if (role.equalsIgnoreCase(MANAGER.toString())) newUser.setRole(MANAGER);
        userService.addUser(newUser);
        pauseBeforeExit();
    }

    public void editUser(UserService userService) {
        userServiceIsNull(userService);
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
            return;
        } else System.out.println(userService.getUsers());
        System.out.println("Enter the id of the user you would like to edit");
        int id = scanner.nextInt();
        System.out.println("Enter username");
        String username = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();
        System.out.println("Enter email");
        String email = scanner.next();
        System.out.println("Enter number of purchases");
        int purchases = scanner.nextInt();
        System.out.println("Enter role");
        String role = scanner.next();
        User user = new User(username, password, email, purchases, User.Role.CLIENT);
        if (role.equalsIgnoreCase(ADMINISTRATOR.toString())) user.setRole(ADMINISTRATOR);
        else if (role.equalsIgnoreCase(MANAGER.toString())) user.setRole(MANAGER);
        userService.editUser(id, user);
        pauseBeforeExit();
    }

    public void removeUser(UserService userService) {
        userServiceIsNull(userService);
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
            return;
        } else System.out.println(userService.getUsers());
        System.out.println("Enter the id of the user you want to cancel");
        int id = scanner.nextInt();
        userService.removeUser(id);
        pauseBeforeExit();
    }

    public static void userServiceIsNull(UserService userService) {
        if (userService == null) {
            LOGGER.log(Level.SEVERE,"userService is null");
            throw new IllegalArgumentException(("userService is null"));
        }
    }
}
