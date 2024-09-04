package com.soroko.carshop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;



/**
 * This class consists fields to store order data to database
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@Getter
@Setter
@Component
public class Order {
    /**
     * Basic fields of the order
     */
    private int id;
    private User user;
    private Car car;
    private Status status;
    private LocalDate createdAt;

    /**
     * Constructor which required to create an order
     * @param user user of the order
     * @param car car of the order
     * @param status status of the order
     */
    public Order(User user, Car car, Status status) {
        if (user == null) {
            log.info("User is null");
            throw new IllegalArgumentException("User is null");
        }
        if (car == null) {
            log.info("Car is null");
            throw new IllegalArgumentException("Car is null");
        }
        if (status == null) {
            log.info("Status is null");
            throw new IllegalArgumentException("Status is null");
        }
        this.user = user;
        this.car = car;
        this.status = status;
        this.createdAt = LocalDate.now();
    }

    public Order() {
    }

    /**
     * Text representation of the order object
     * @return returns String
     */
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

    /**
     * Enum class that required to set specific order status
     */
    public enum Status {
        CREATED, IN_PROGRESS, COMPLETED;
    }
}