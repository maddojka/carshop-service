package com.soroko.carshop.service;

import com.soroko.carshop.entity.Car;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 */
public class CarService {
    private final List<Car> cars = new ArrayList<>();

    /**
     * Add car to collection
     */
    public void addCar(Car car) {
        if (car == null) {
            LOGGER.severe("car is null");
            throw new IllegalArgumentException("car is null");
        }
        this.cars.add(car);
        LOGGER.info("Car was added");
    }

    /**
     * Get car by id
     */
    public Car getCar(int id) {
        if (id < 0 || id >= this.cars.size()) {
            LOGGER.severe("Car id does not exist");
            throw new IllegalArgumentException("Car id does not exist");
        }
        return cars.get(id);
    }

    /**
     * Get all cars from collection
     */
    public List<Car> getCars() {
        return this.cars;
    }


    /**
     * Edit car by id
     */
    public void editCar(int id, Car car) {
        if (car == null || id < 0 || id >= this.cars.size()) {
            LOGGER.severe("Car id does not exist");
            throw new IllegalArgumentException("Car id does not exist");
        }
        this.cars.get(id).setMake(car.getMake());
        this.cars.get(id).setModel(car.getModel());
        this.cars.get(id).setYear(car.getYear());
        this.cars.get(id).setPrice(car.getPrice());
        this.cars.get(id).setCondition(car.getCondition());
        LOGGER.info("Car was updated");
    }

    /**
     * Sell car by id
     */
    public void sellCar(int id) {
        if (id < 0 || id >= this.cars.size()) {
            LOGGER.severe("Id cannot be negative");
            throw new IllegalArgumentException("Id cannot be negative");
        }
        this.cars.remove(id);
        LOGGER.info("Car was deleted");
    }

    /**
     * Find and sort cars by make
     */
    public List<Car> findAndSortBy(String make) {
        if ("".equals(make) || make == null) {
            LOGGER.severe("make is empty");
            throw new IllegalArgumentException("make is empty");
        }
        return cars.stream().filter(x -> x.getMake().equals(make)).toList();
    }

    /**
     * Find and sort cars by year
     */
    public List<Car> findAndSortBy(int year) {
        if (year < 2000 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            LOGGER.severe("Car year does not exist");
            throw new IllegalArgumentException("Car year does not exist");
        }
        return cars.stream().filter(x -> x.getYear() == year)
                .sorted(Comparator.comparingInt(Car::getYear)).toList();
    }

    /**
     * Find and sort cars by price
     */
    public List<Car> findAndSortBy(double price) {
        if (price < 0) {
            LOGGER.severe("price is negative");
            throw new IllegalArgumentException("price is negative");
        }
        return cars.stream().filter(x -> x.getPrice() <= price).sorted().toList();
    }

    /**
     * Find cars by model
     */
    public List<Car> findByModel(String model) {
        if ("".equals(model) || model == null) {
            LOGGER.severe("model is null");
            throw new IllegalArgumentException("model is null");
        }
        return cars.stream().filter(x -> x.getModel().equals(model)).toList();
    }

    /**
     * Find cars by condition
     */
    public List<Car> findByCondition(String condition) {
        if ("".equals(condition) || condition == null) {
            LOGGER.severe("condition is empty");
            throw new IllegalArgumentException("condition is empty");
        }
        return cars.stream().filter(x -> x.getCondition().equals(condition)).toList();
    }

    /**
     * Find cars by condition and price
     */
    public List<Car> findByConditionAndPrice(String condition, double price) {
        if ("".equals(condition) || condition == null || price < 0) {
            LOGGER.severe("condition is empty or price is negative");
            throw new IllegalArgumentException("condition is empty or price is negative");
        }
        return cars.stream().filter(x -> x.getCondition().equals(condition))
                .filter(x -> x.getPrice() < price).toList();
    }
}
