package com.soroko.carshop.mapper;

import com.soroko.carshop.dto.UserDTO;
import com.soroko.carshop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class); //add instance to call mapper

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOList(List<User> users);
}
