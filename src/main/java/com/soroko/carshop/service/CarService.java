package com.soroko.carshop.service;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.repository.CarRepository;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarService {

    private final CarRepository carRepository = new CarRepository();

    public CarService() throws SQLException {
    }

    /**
     * Add car to collection
     *
     * @param car - car to add
     */
    public void addCar(Car car) throws SQLException {
        if (car == null) {
            LOGGER.severe("car is null");
            throw new IllegalArgumentException("car is null");
        }
        carRepository.insert(car);
        LOGGER.info("Car was added");
    }

    /**
     * Get car by id
     *
     * @param id - id of the car to get
     * @return car - requested Car
     */
    public Car getCar(int id) throws SQLException {
        if (id < 0) {
            LOGGER.severe("Car id does not exist");
            throw new IllegalArgumentException("Car id does not exist");
        }
        return carRepository.findById(id);
    }

    /**
     * Get all cars from collection
     *
     * @return List of the available cars
     */
    public List<Car> getCars() throws SQLException {
        return carRepository.getAllCars();
    }


    /**
     * Edit car by id
     *
     * @param car - form of the car to edit
     */
    public void editCar(Car car) throws SQLException {
        if (car == null) {
            LOGGER.severe("Car does not exist");
            throw new IllegalArgumentException("Car does not exist");
        }
        Car carToEdit = carRepository.findById(car.getId());
        carToEdit.setId(car.getId());
        carToEdit.setMake(car.getMake());
        carToEdit.setModel(car.getModel());
        carToEdit.setYear(car.getYear());
        carToEdit.setPrice(car.getPrice());
        carToEdit.setCondition(car.getCondition());
        carRepository.update(carToEdit);
        LOGGER.info("Car was updated");
    }

    /**
     * Sell car by id
     *
     * @param id - id of the car to remove from the collection
     */
    public void sellCar(int id) throws SQLException {
        if (id < 0) {
            LOGGER.severe("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        carRepository.deleteById(id);
        LOGGER.info("Car was deleted");
    }

    /**
     * Find and sort cars by make
     *
     * @param make - filter by make which was requested
     * @return List of the filtered and sorted cars
     */
    public List<Car> findAndSortBy(String make) throws SQLException {
        if ("".equals(make) || make == null) {
            LOGGER.severe("make is empty");
            throw new IllegalArgumentException("make is empty");
        }
        List<Car> cars = carRepository.getAllCars();
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
            LOGGER.severe("Car year does not exist");
            throw new IllegalArgumentException("Car year does not exist");
        }
        List<Car> cars = carRepository.getAllCars();
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
            LOGGER.severe("price is negative");
            throw new IllegalArgumentException("price is negative");
        }
        List<Car> cars = carRepository.getAllCars();
        return cars.stream().filter(x -> x.getPrice() <= price).sorted().toList();
    }

    /**
     * Find cars by model
     *
     * @param model - filter by model which was requested
     * @return List of the filtered cars
     */
    public List<Car> findByModel(String model) throws SQLException {
        if ("".equals(model) || model == null) {
            LOGGER.severe("model is null");
            throw new IllegalArgumentException("model is null");
        }
        List<Car> cars = carRepository.getAllCars();
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
            LOGGER.severe("condition is empty");
            throw new IllegalArgumentException("condition is empty");
        }
        List<Car> cars = carRepository.getAllCars();
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
            LOGGER.severe("condition is empty or price is negative");
            throw new IllegalArgumentException("condition is empty or price is negative");
        }
        List<Car> cars = carRepository.getAllCars();
        return cars.stream().filter(x -> x.getCondition().equals(condition))
                .filter(x -> x.getPrice() < price).toList();
    }
}
