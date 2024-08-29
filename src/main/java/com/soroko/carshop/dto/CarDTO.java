package com.soroko.carshop.dto;

/**
 * This class consists car data transfer object fields
 * it needs to transform input data to entity
 * @author yuriy.soroko
 * @version 1.0
 */
public record CarDTO(
        int id,
        String make,
        String model,
        int year,
        double price,
        String condition) {
}
