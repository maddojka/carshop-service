package com.soroko.carshop.dto;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;

/**
 * @author yuriy.soroko
 */
public record OrderDTO(
        User user,
        Car car,
        Order.Status status) {
}
