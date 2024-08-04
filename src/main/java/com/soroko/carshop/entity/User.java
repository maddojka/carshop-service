package com.soroko.carshop.entity;

import java.util.Objects;
import java.util.logging.Level;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 */
public class User {
    public static int USER_COUNT;
    private int userId;
    private String username;
    private String password;
    private String email;
    private int numberOfpurchases;
    private Role role;

    public User(String username, String password, String email, int numberOfpurchases, Role role) {
        if ("".equals(username) || username == null) {
            LOGGER.log(Level.SEVERE, "Username is empty");
            throw new IllegalArgumentException("Username is empty");
        }
        if ("".equals(password) || password == null) {
            LOGGER.log(Level.SEVERE, "Password is empty");
            throw new IllegalArgumentException("Password is empty");
        }
        this.userId = USER_COUNT++;
        this.username = username;
        this.password = password;
        setEmail(email);
        this.email = email;
        setNumberOfpurchases(numberOfpurchases);
        this.numberOfpurchases = numberOfpurchases;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if("".equals(email) || email == null) {
            LOGGER.log(Level.SEVERE, "Email is empty");
            throw new IllegalArgumentException("Email is empty");
        }
        this.email = email;
    }

    public int getNumberOfpurchases() {
        return numberOfpurchases;
    }

    public void setNumberOfpurchases(int numberOfpurchases) {
        if(numberOfpurchases < 0) {
            LOGGER.log(Level.SEVERE, "Number of purchases cannot be negative");
            throw new IllegalArgumentException("Number of purchases cannot be negative");
        }
        this.numberOfpurchases = numberOfpurchases;
    }

    public Role getRole() {
        return role;
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
                ", numberOfpurchases=" + numberOfpurchases +
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

