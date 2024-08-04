package com.soroko.carshop.entity;

import java.time.LocalDate;
import java.util.logging.Level;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 */
public class Order {
    public static int ORDER_COUNT;
    private int orderId;
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
        this.orderId = ORDER_COUNT++;
        this.user = user;
        this.car = car;
        this.status = status;
        this.createdAt = LocalDate.now();
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
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
