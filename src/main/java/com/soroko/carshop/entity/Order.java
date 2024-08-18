package com.soroko.carshop.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.logging.Level;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class Order {

    private int id;

    private User user;

    private Car car;

    private Status status;

    private LocalDate createdAt;

    public Order(User user, Car car, Status status) {
        if (user == null) {
            LOGGER.log(Level.SEVERE, "user is null");
            throw new IllegalArgumentException("user is null");
        }
        if (car == null) {
            LOGGER.log(Level.SEVERE, "car is null");
            throw new IllegalArgumentException("car is null");
        }
        if (status == null) {
            LOGGER.log(Level.SEVERE, "status is null");
            throw new IllegalArgumentException("status is null");
        }
        this.user = user;
        this.car = car;
        this.status = status;
        this.createdAt = LocalDate.now();
    }

    public Order() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" +
                ", user=" + user +
                ", car=" + car +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    public enum Status {
        CREATED, IN_PROGRESS, COMPLETED;
    }
}
