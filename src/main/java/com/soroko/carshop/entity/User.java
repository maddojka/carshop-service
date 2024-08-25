package com.soroko.carshop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.logging.Level;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 * @version 1.0
 */
@Getter
@Setter
@Component
public class User {

    private int id;

    private String username;

    private String password;

    private String email;

    private int numberOfPurchases;

    private Role role;

    public User(String username, String password, String email, int numberOfPurchases, Role role) {
        if ("".equals(username) || username == null) {
            LOGGER.log(Level.SEVERE, "Username is empty");
            throw new IllegalArgumentException("Username is empty");
        }
        if ("".equals(password) || password == null) {
            LOGGER.log(Level.SEVERE, "Password is empty");
            throw new IllegalArgumentException("Password is empty");
        }
        this.username = username;
        this.password = password;
        setEmail(email);
        this.email = email;
        setNumberOfPurchases(numberOfPurchases);
        this.numberOfPurchases = numberOfPurchases;
        setRole(role);
        this.role = role;
    }

    public User(String username, String password, String email, Role role) {
        if ("".equals(username) || username == null) {
            LOGGER.log(Level.SEVERE, "Username is empty");
            throw new IllegalArgumentException("Username is empty");
        }
        if ("".equals(password) || password == null) {
            LOGGER.log(Level.SEVERE, "Password is empty");
            throw new IllegalArgumentException("Password is empty");
        }
        this.username = username;
        this.password = password;
        setEmail(email);
        this.email = email;
        setRole(role);
        this.role = role;
    }

    public User() {
    }

    public void setEmail(String email) {
        if ("".equals(email) || email == null) {
            LOGGER.log(Level.SEVERE, "Email is empty");
            throw new IllegalArgumentException("Email is empty");
        }
        this.email = email;
    }

    public void setNumberOfPurchases(int numberOfPurchases) {
        if (numberOfPurchases < 0) {
            LOGGER.log(Level.SEVERE, "Number of purchases cannot be negative");
            throw new IllegalArgumentException("Number of purchases cannot be negative");
        }
        this.numberOfPurchases = numberOfPurchases;
    }

    public void setRole(Role role) {
        if (role == null) {
            LOGGER.log(Level.SEVERE, "Role is null");
            throw new IllegalArgumentException("Role is null");
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", numberOfpurchases=" + numberOfPurchases +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }


    public enum Role {
        ADMINISTRATOR, MANAGER, CLIENT;
    }
}

