package com.soroko.carshop.controller;
import com.soroko.auditstarter.annotations.EnableLoggable;
import com.soroko.carshop.dto.CarDTO;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.mapper.CarMapper;
import com.soroko.carshop.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * This class consists REST API logic of cars
 *
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@EnableLoggable
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
@Tag(
        name = "Cars",
        description = "All methods to work with cars data of the system"
)
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    /**
     * Get all cars from the service
     *
     * @return returns list of all modified cars by mapper
     */
    @GetMapping("/all")
    @Operation(summary = "Get information about all cars")
    public ResponseEntity<List<CarDTO>> getAllCars() throws SQLException {
        List<Car> cars = carService.getCars();
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        return ResponseEntity.ok(carsDTO);
    }

    /**
     * Get car by id
     *
     * @param id id of car
     * @return returns car data
     */
    @GetMapping("/get")
    @Operation(summary = "Get information about car by id")
    public ResponseEntity<CarDTO> getCarById(
            @Parameter(description = "car id", example = "1")
            @RequestParam int id
            ) throws SQLException {
        var car = carService.getCar(id);
        if (car == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        CarDTO carDTO = carMapper.toCarDTO(car);
        return ResponseEntity.ok(carDTO);
    }

    /**
     * Add car to the system
     *
     * @param carDTO car you need to add in the system
     * @return returns car which was added
     */
    @PostMapping("/add")
    @Operation(summary = "Add car to the system")
    public ResponseEntity<Car> addCar(@RequestBody CarDTO carDTO) throws SQLException {
        var car = carMapper.toCar(carDTO);
        carService.addCar(car);
        log.info("Adding new car: {}", car);
        return ResponseEntity.ok(car);
    }

    /**
     * @param carDTO car you need to edit in the system
     * @return returns car which was edited
     */
    @PatchMapping("/edit")
    @Operation(summary = "Edit existing car of the system")
    public ResponseEntity<Car> editCar(@RequestBody CarDTO carDTO) throws SQLException {
        var car = carMapper.toCar(carDTO);
        carService.editCar(car);
        log.info("Updating car: {}", car);
        return ResponseEntity.ok(car);
    }

    /**
     * @param id id of the car that you need to delete from the system
     */
    @DeleteMapping("/delete")
    @Operation(summary = "Delete car from the system")
    public void deleteCar(
                          @Parameter(description = "car id", example = "1")
                          @RequestParam int id
                          ) throws SQLException {
        carService.sellCar(id);
    }

    /**
     * @param condition condition to filter
     * @param price     price to filter
     * @return returns list of all filtered cars by condition and price
     */
    @GetMapping("/getbyconditionandprice")
    @Operation(summary = "Get car from the system by condition and price")
    public ResponseEntity<List<CarDTO>> getCarByConditionAndPrice(
            @RequestParam
            @Parameter(description = "condition", example = "new")
            String condition,
            @RequestParam @Parameter(description = "price", example = "1000000")
            double price) throws SQLException {
        List<Car> cars = carService.findByConditionAndPrice(condition, price);
        if (cars == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        return ResponseEntity.ok(carsDTO);
    }

    /**
     * @param condition condition to filter
     * @return returns list of all filtered cars by condition
     */
    @GetMapping("/getbycondition")
    @Operation(summary = "Get car from the system by condition")
    public ResponseEntity<List<CarDTO>> getCarByCondition(@RequestParam
                                                          @Parameter(description = "condition", example = "new") String condition)
            throws SQLException {
        List<Car> cars = carService.findByCondition(condition);
        if (cars == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        return ResponseEntity.ok(carsDTO);
    }

    /**
     * @param make make to filter
     * @return returns list of all filtered cars by make
     */
    @GetMapping("/getbymake")
    @Operation(summary = "Get car from the system by make")
    public ResponseEntity<List<CarDTO>> getCarByMake(@RequestParam
                                                     @Parameter(description = "make", example = "Lada") String make)
            throws SQLException {
        List<Car> cars = carService.findByCondition(make);
        if (cars == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        return ResponseEntity.ok(carsDTO);
    }

    /**
     * @param model model to filter
     * @return returns list of all filtered cars by model
     */
    @GetMapping("/getbymodel")
    @Operation(summary = "Get car from the system by model")
    public ResponseEntity<List<CarDTO>> getCarByModel(@RequestParam
                                                      @Parameter(description = "model", example = "Granta")
                                                      String model) throws SQLException {
        List<Car> cars = carService.findByModel(model);
        if (cars == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        return ResponseEntity.ok(carsDTO);
    }

    /**
     * @param price price to filter
     * @return returns list of all filtered cars by price
     */
    @GetMapping("/getbyprice")
    @Operation(summary = "Get car from the system by price")
    public ResponseEntity<List<CarDTO>> getCarByPrice(
            @RequestParam
            @Parameter(description = "price", example = "1000000") double price)
            throws SQLException {
        List<Car> cars = carService.findAndSortBy(price);
        if (cars == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        return ResponseEntity.ok(carsDTO);
    }

    /**
     * @param year year to filter
     * @return returns list of all filtered cars by year
     */
    @GetMapping("/getbyyear")
    @Operation(summary = "Get car from the system by year")
    public ResponseEntity<List<CarDTO>> getCarByYear(@RequestParam
                                                     @Parameter(description = "year", example = "2024") int year)
            throws SQLException {
        List<Car> cars = carService.findAndSortBy(year);
        if (cars == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        return ResponseEntity.ok(carsDTO);
    }
}
