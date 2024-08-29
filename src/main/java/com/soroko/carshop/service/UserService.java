package com.soroko.carshop.service;

import com.soroko.carshop.annotations.Loggable;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;

/**
 * This class consists logic of users data
 * @author yuriy.soroko
 * @version 1.0
 */
@Loggable
@Service
public class UserService {
    /**
     * inject user repository to put and get data from database
     */
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Add user to collection
     *
     * @param user - user to add
     */
    public void addUser(User user) throws SQLException {
        if (user == null) {
            LOGGER.severe("User is null");
            throw new IllegalArgumentException("User is null");
        }
        this.userRepository.insert(user);
        LOGGER.info("User was added");
    }

    /**
     * Get user by id
     *
     * @param id - id of the order to get
     * @return user - requested User
     */
    public User getUser(int id) throws SQLException {
        if (id < 0) {
            LOGGER.severe("User not found");
            throw new IllegalArgumentException("User not found");
        }
        return this.userRepository.findById(id);
    }

    /**
     * Get all users from collection
     *
     * @return List of the available cars
     */
    public List<User> getUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    /**
     * Edit user by id
     *
     * @param user - form of the user to edit
     */
    public void editUser(User user) throws SQLException {
        if (user == null) {
            LOGGER.severe("User not found");
            throw new IllegalArgumentException("User not found");
        }
        User userToEdit = userRepository.findById(user.getId());
        userToEdit.setId(user.getId());
        userToEdit.setUsername(user.getUsername());
        userToEdit.setPassword(user.getPassword());
        userToEdit.setEmail(user.getEmail());
        userToEdit.setNumberOfPurchases(user.getNumberOfPurchases());
        userToEdit.setRole(user.getRole());
        userRepository.update(userToEdit);
        LOGGER.info("User was edited");
    }

    /**
     * Remove user by id
     *
     * @param id - id of the user to remove from the collection
     */
    public void removeUser(int id) throws SQLException {
        if (id < 0) {
            LOGGER.severe("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        userRepository.deleteById(id);
        LOGGER.info("User was removed");
    }
}
