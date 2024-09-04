package com.soroko.carshop.mapper;

import com.soroko.carshop.dto.CarDTO;
import com.soroko.carshop.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * transform Car data transfer object to Car data and vise versa
 * @author yuriy.soroko
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDTO toCarDTO(Car car);

    Car toCar(CarDTO carDTO);

    List<CarDTO> toCarDTOList(List<Car> cars);
}
