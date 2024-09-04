package com.soroko.carshop.dto;

import com.soroko.carshop.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This class consists user data transfer object fields
 * it needs to transform input data to entity
 * @author yuriy.soroko
 * @version 1.0
 */
@Schema(description = "Information about user")
public record UserDTO(
        @Schema(description = "id", example = "1")
        int id,
        @Schema(description = "username of the account", example = "admin01")
        String username,
        @Schema(description = "email", example = "user@example.com")
        String email,
        @Schema(description = "role of the user", example = "manager")
        User.Role role,
        @Schema(description = "number of purchases", example = "10")
        int numberOfPurchases) {

}
