package com.soroko.carshop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * This class consists fields to store user data to database
 *
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@Getter
@Setter
@Component
public class User {
    /**
     * Basic fields of the user
     */
    private int id;
    private String username;
    private String password;
    private String email;
    private int numberOfPurchases;
    private Role role;

    /**
     * Constructors which required to create a user
     * @param username username of the user
     * @param password password of the user
     * @param email email of the user
     * @param numberOfPurchases number of purchases of the user
     * @param role role of the user
     */
    public User(String username, String password, String email, int numberOfPurchases, Role role) {
        if ("".equals(username) || username == null) {
            log.info("Username is empty");
            throw new IllegalArgumentException("Username is empty");
        }
        if ("".equals(password) || password == null) {
            log.info("Password is empty");
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
            log.info("Username is empty");
            throw new IllegalArgumentException("Username is empty");
        }
        if ("".equals(password) || password == null) {
            log.info("Password is empty");
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

    // getters and setters
    public void setEmail(String email) {
        if ("".equals(email) || email == null) {
            log.info("Email is empty");
            throw new IllegalArgumentException("Email is empty");
        }
        this.email = email;
    }

    public void setNumberOfPurchases(int numberOfPurchases) {
        if (numberOfPurchases < 0) {
            log.info("Number of purchases cannot be negative");
            throw new IllegalArgumentException("Number of purchases cannot be negative");
        }
        this.numberOfPurchases = numberOfPurchases;
    }

    public void setRole(Role role) {
        if (role == null) {
            log.info("Role is null");
            throw new IllegalArgumentException("Role is null");
        }
        this.role = role;
    }

    /**
     * Text representation of the user object
     * @return returns String
     */
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


    /**
     * Equals and hashcode methods which compares two objects
     */
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

    /**
     * Enum class that required to set specific user roles
     */
    public enum Role {
        ADMINISTRATOR, MANAGER, CLIENT;
    }
}
