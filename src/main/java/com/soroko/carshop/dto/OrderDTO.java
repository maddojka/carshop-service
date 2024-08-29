package com.soroko.carshop.dto;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;

import java.time.LocalDate;

/**
 * This class consists order data transfer object fields
 * it needs to transform input data to entity
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
