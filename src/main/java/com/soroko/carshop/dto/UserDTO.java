package com.soroko.carshop.dto;

import com.soroko.carshop.entity.User;

/**
 * This class consists user data transfer object fields
 * it needs to transform input data to entity
 * @author yuriy.soroko
 * @version 1.0
 */
public record UserDTO(
        int id,
        String username,
        String email,
        User.Role role,
        int numberOfPurchases) {

}
