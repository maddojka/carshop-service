package com.soroko.carshop.controller;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.UserService;

import java.util.Scanner;

import static com.soroko.carshop.entity.User.Role.ADMINISTRATOR;
import static com.soroko.carshop.entity.User.Role.MANAGER;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class UserController {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
     * receive all available users
     */
    public void getUsers() {
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
        } else System.out.println(userService.getUsers());
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * register new user in the system
     */
    public void registerUser() {
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

    /**
     * This method is an addition layer with scanner functionality
     * edit existing user
     */
    public void editUser() {
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

    /**
     * This method is an addition layer with scanner functionality
     * remove existing user from the system
     */
    public void removeUser() {
        if (userService.getUsers().isEmpty()) {
            System.out.println("No users found");
            return;
        } else System.out.println(userService.getUsers());
        System.out.println("Enter the id of the user you want to cancel");
        int id = scanner.nextInt();
        userService.removeUser(id);
        pauseBeforeExit();
    }
}
