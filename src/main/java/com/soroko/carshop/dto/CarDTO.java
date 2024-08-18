package com.soroko.carshop.dto;

/**
 * @author yuriy.soroko
 */
public record CarDTO(
        String make,
        String model,
        int year,
        double price,
        String condition) {
}
