package com.soroko.carshop.service;

import com.soroko.carshop.entity.User;

import java.util.ArrayList;
import java.util.List;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;

/**
 * @author yuriy.soroko
 */
public class UserService {
    private final List<User> users = new ArrayList<>();

    /**
     * Add user to collection
     */
    public void addUser(User user) {
        if (users.contains(user)) {
            LOGGER.warning("User already exists");
            return;
        }
        if (user == null) {
            LOGGER.severe("User is null");
            throw new IllegalArgumentException("User is null");
        }
        this.users.add(user);
        LOGGER.info("User was added");
    }

    /**
     * Get user by id
     */
    public User getUser(int id) {
        if (id < 0 || id >= this.users.size()) {
            LOGGER.severe("User not found");
            throw new IllegalArgumentException("User not found");
        }
        return this.users.get(id);
    }

    /**
     * Get all users from collection
     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Edit user by id
     */
    public void editUser(int id, User user) {
        if (user == null || id < 0 || id >= this.users.size()) {
            LOGGER.severe("User not found");
            throw new IllegalArgumentException("User not found");
        }
        this.users.get(id).setUsername(user.getUsername());
        this.users.get(id).setPassword(user.getPassword());
        this.users.get(id).setEmail(user.getEmail());
        this.users.get(id).setNumberOfpurchases(user.getNumberOfpurchases());
        this.users.get(id).setRole(user.getRole());
        LOGGER.info("User was edited");
    }

    /**
     * Remove user by id
     */
    public void removeUser(int id) {
        if (id < 0 || id >= this.users.size()) {
            LOGGER.severe("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        this.users.remove(id);
        LOGGER.info("User was removed");
    }
}
