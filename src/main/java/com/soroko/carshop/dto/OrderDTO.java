package com.soroko.carshop.dto;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;

import java.time.LocalDate;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public record OrderDTO(
        int id,
        User user,
        Car car,
        Order.Status status,
        LocalDate createdAt) {
}
