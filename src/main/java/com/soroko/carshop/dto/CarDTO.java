package com.soroko.carshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This class consists car data transfer object fields
 * it needs to transform input data to entity
 * @author yuriy.soroko
 * @version 1.0
 */
@Schema(description = "Information about car")
public record CarDTO(
        @Schema(description = "id", example = "1")
        int id,
        @Schema(description = "make", example = "Lada")
        String make,
        @Schema(description = "model", example = "Granta")
        String model,
        @Schema(description = "year", example = "2010")
        int year,
        @Schema(description = "price", example = "100000")
        double price,
        @Schema(description = "condition", example = "new")
        String condition) {
}
