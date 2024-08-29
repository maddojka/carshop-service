package com.soroko.carshop.controller;

import com.soroko.carshop.annotations.Loggable;
import com.soroko.carshop.dto.CarDTO;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.mapper.CarMapper;
import com.soroko.carshop.service.CarService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * This class consists REST API logic of cars
 * @author yuriy.soroko
 * @version 1.0
 */
@Loggable
@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @Autowired
    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    /**
     * Get all cars from the service
     *
     * @return returns list of all modified cars by mapper
     */
    @GetMapping("/all")
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
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable int id) throws SQLException {
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
    public ResponseEntity<Car> addCar(@RequestBody CarDTO carDTO) throws SQLException {
        var car = carMapper.toCar(carDTO);
        carService.addCar(car);
        return ResponseEntity.ok(car);
    }

    /**
     * @param carDTO car you need to edit in the system
     * @return returns car which was edited
     */
    @PatchMapping("/edit")
    public ResponseEntity<Car> editCar(@RequestBody CarDTO carDTO) throws SQLException {
        var car = carMapper.toCar(carDTO);
        carService.editCar(car);
        return ResponseEntity.ok(car);
    }

    /**
     * @param id id of the car that you need to delete from the system
     */
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id) throws SQLException {
        carService.sellCar(id);
    }

    /**
     * @param condition condition to filter
     * @param price     price to filter
     * @return returns list of all filtered cars by condition and price
     */
    @GetMapping("/getbyconditionandprice")
    public ResponseEntity<List<CarDTO>> getCarByConditionAndPrice(@RequestParam String condition,
                                                                  @RequestParam double price) throws SQLException {
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
    public ResponseEntity<List<CarDTO>> getCarByCondition(@RequestParam String condition) throws SQLException {
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
    public ResponseEntity<List<CarDTO>> getCarByMake(@RequestParam String make) throws SQLException {
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
    public ResponseEntity<List<CarDTO>> getCarByModel(@RequestParam String model) throws SQLException {
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
    @GetMapping("getbyprice")
    public ResponseEntity<List<CarDTO>> getCarByPrice(@RequestParam double price) throws SQLException {
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
    @GetMapping("getbyyear")
    public ResponseEntity<List<CarDTO>> getCarByYear(@RequestParam int year) throws SQLException {
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
