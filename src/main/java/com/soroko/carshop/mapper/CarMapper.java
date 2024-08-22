package com.soroko.carshop.mapper;

import com.soroko.carshop.dto.CarDTO;
import com.soroko.carshop.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class); //add instance to call mapper

    CarDTO toCarDTO(Car car);

    List<CarDTO> toCarDTOList(List<Car> cars);
}
