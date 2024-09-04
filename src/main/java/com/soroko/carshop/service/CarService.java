package com.soroko.carshop.service;

import com.soroko.auditstarter.annotations.EnableLoggable;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;


/**
 * This class consists logic of cars data
 *
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@EnableLoggable
@Service
public class CarService {
    /**
     * inject user repository to put and get data from database
     */
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Add car to collection
     *
     * @param car - car to add
     */
    public void addCar(Car car) throws SQLException {
        if (car == null) {
            log.error("car is null");
            throw new IllegalArgumentException("car is null");
        }
        carRepository.save(car);
        log.info("Car was added");
    }

    /**
     * Get car by id
     *
     * @param id - id of the car to get
     * @return car - requested Car
     */
    public Car getCar(int id) throws SQLException {
        if (id < 0) {
            log.error("Car id does not exist");
            throw new IllegalArgumentException("Car id does not exist");
        }
        return carRepository.getById(id);
    }

    /**
     * Get all cars from collection
     *
     * @return List of the available cars
     */
    public List<Car> getCars() throws SQLException {
        return carRepository.findAll();
    }


    /**
     * Edit car by id
     *
     * @param car - form of the car to edit
     */
    public void editCar(Car car) throws SQLException {
        if (car == null) {
            log.error("Car does not exist");
            throw new IllegalArgumentException("Car does not exist");
        }
        Car carToEdit = carRepository.getById(car.getId());
        carToEdit.setId(car.getId());
        carToEdit.setMake(car.getMake());
        carToEdit.setModel(car.getModel());
        carToEdit.setYear(car.getYear());
        carToEdit.setPrice(car.getPrice());
        carToEdit.setCondition(car.getCondition());
        carRepository.save(carToEdit);
        log.info("Car was updated");
    }

    /**
     * Sell car by id
     *
     * @param id - id of the car to remove from the collection
     */
    public void sellCar(int id) throws SQLException {
        if (id < 0) {
            log.error("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        carRepository.deleteById(id);
        log.info("Car was deleted");
    }

    /**
     * Find and sort cars by make
     *
     * @param make - filter by make which was requested
     * @return List of the filtered and sorted cars
     */
    public List<Car> findAndSortBy(String make) throws SQLException {
        if ("".equals(make) || make == null) {
            log.error("make is empty");
            throw new IllegalArgumentException("make is empty");
        }
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(x -> x.getMake().equals(make)).toList();
    }

    /**
     * Find and sort cars by year
     *
     * @param year - filter by year which was requested
     * @return List of the filtered and sorted cars
     */
    public List<Car> findAndSortBy(int year) throws SQLException {
        if (year < 2000 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            log.error("Car year does not exist");
            throw new IllegalArgumentException("Car year does not exist");
        }
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(x -> x.getYear() == year)
                .sorted(Comparator.comparingInt(Car::getYear)).toList();
    }

    /**
     * Find and sort cars by price
     *
     * @param price - filter by price which was requested
     * @return List of the filtered and sorted cars
     */
    public List<Car> findAndSortBy(double price) throws SQLException {
        if (price < 0) {
            log.error("price is negative");
            throw new IllegalArgumentException("price is negative");
        }
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(x -> x.getPrice() <= price)
                .sorted(Comparator.comparingDouble(Car::getPrice)).toList();
    }

    /**
     * Find cars by model
     *
     * @param model - filter by model which was requested
     * @return List of the filtered cars
     */
    public List<Car> findByModel(String model) throws SQLException {
        if ("".equals(model) || model == null) {
            log.error("model is null");
            throw new IllegalArgumentException("model is null");
        }
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(x -> x.getModel().equals(model)).toList();
    }

    /**
     * Find cars by condition
     *
     * @param condition - filter by condition which was requested
     * @return List of the filtered cars
     */
    public List<Car> findByCondition(String condition) throws SQLException {
        if ("".equals(condition) || condition == null) {
            log.error("condition is empty");
            throw new IllegalArgumentException("condition is empty");
        }
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(x -> x.getCondition().equals(condition)).toList();
    }

    /**
     * Find cars by condition and price
     *
     * @param condition - filter by condition and price which was requested
     * @return List of the filtered cars
     */
    public List<Car> findByConditionAndPrice(String condition, double price) throws SQLException {
        if ("".equals(condition) || condition == null || price < 0) {
            log.error("condition is empty or price is negative");
            throw new IllegalArgumentException("condition is empty or price is negative");
        }
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(x -> x.getCondition().equals(condition))
                .filter(x -> x.getPrice() < price).toList();
    }
}
