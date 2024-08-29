package com.soroko.carshop.mapper;

import com.soroko.carshop.dto.UserDTO;
import com.soroko.carshop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * transform User data transfer object to Car data and vise versa
 * @author yuriy.soroko
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class); //add instance to call mapper

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<User> users);
}
