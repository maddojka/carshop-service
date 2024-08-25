package com.soroko.carshop.dto;

import com.soroko.carshop.entity.User;

/**
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
