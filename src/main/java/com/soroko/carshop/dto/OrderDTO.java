package com.soroko.carshop.dto;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * This class consists order data transfer object fields
 * it needs to transform input data to entity
 *
 * @author yuriy.soroko
 * @version 1.0
 */
@Schema(description = "Information about order")
public record OrderDTO(
        @Schema(description = "id", example = "1")
        int id,
        User user,
        Car car,
        @Schema(description = "status of the order", example = "created")
        Order.Status status,
        @Schema(description = "date of order creation", example = "2024-01-01")
        LocalDate createdAt) {
}
