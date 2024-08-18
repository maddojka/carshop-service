package com.soroko.carshop.dto;

import com.soroko.carshop.entity.User;

/**
 * @author yuriy.soroko
 */
public record UserDTO(
        String username,
        String email,
        User.Role role,
        int numberOfpurchases) {

}
