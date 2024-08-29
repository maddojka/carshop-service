package com.soroko.carshop.mapper;

import com.soroko.carshop.dto.OrderDTO;
import com.soroko.carshop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * transform Order data transfer object to Order data and vise versa
 * @author yuriy.soroko
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class); //add instance to call mapper

    OrderDTO toOrderDTO(Order order);

    Order toOrder(OrderDTO orderDTO);

    List<OrderDTO> toOrderDTOList(List<Order> orders);
}
