package com.soroko.carshop.dto;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public record CarDTO(
        String make,
        String model,
        int year,
        double price,
        String condition) {
}
