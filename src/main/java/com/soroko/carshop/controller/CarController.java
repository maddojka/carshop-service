package com.soroko.carshop.controller;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.service.CarService;

import java.util.Scanner;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarController {

    private final Scanner scanner = new Scanner(System.in);
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * This method perform a pause function in order to see result of the query
     */
    public String pauseBeforeExit() {
        System.out.println("Enter something to return");
        return scanner.next();
    }

    /**
     * This method is an addition layer with scanner functionality
     * receive all available cars
     */
    public void getCars() {
        if (carService.getCars().isEmpty()) {
            System.out.println("No cars found");
        } else System.out.println(carService.getCars());
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter cars by make
     */
    public void getCarByMake() {
        System.out.println("Enter the make you search");
        String make = scanner.next();
        System.out.println(carService.findAndSortBy(make));
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter cars by model
     */
    public void getCarByModel() {
        System.out.println("Enter the model you search");
        String model = scanner.next();
        System.out.println(carService.findByModel(model));
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter cars by year
     */
    public void getCarByYear() {
        System.out.println("Enter the year you search");
        int year = scanner.nextInt();
        System.out.println(carService.findAndSortBy(year));
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter cars by price
     */
    public void getCarByPrice() {
        System.out.println("Enter the price you search");
        double price = scanner.nextDouble();
        System.out.println(carService.findAndSortBy(price));
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter cars by condition
     */
    public void getCarByCondition() {
        System.out.println("Enter the condition you search");
        String condition = scanner.next();
        System.out.println(carService.findByCondition(condition));
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * filter cars by condition and price
     */
    public void getCarByConditionAndPrice() {
        System.out.println("Enter the condition you search");
        String condition = scanner.next();
        System.out.println("Enter the price you search");
        double price = scanner.nextDouble();
        System.out.println(carService.findByConditionAndPrice(condition, price));
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * register new car in the system
     */
    public void registerCar() {
        System.out.println("Enter the year of the car");
        int year = scanner.nextInt();
        System.out.println("Enter the price of the car");
        double price = scanner.nextDouble();
        System.out.println("Enter the model of the car");
        String model = scanner.next();
        System.out.println("Enter the make of the car");
        String make = scanner.next();
        System.out.println("Enter the condition of the car");
        String condition = scanner.next();
        Car car = new Car(make, model, year, price, condition);
        carService.addCar(car);
        pauseBeforeExit();
    }

    /**
     * This method is an addition layer with scanner functionality
     * edit existing car
     */
    public void editCar() {
        if (carService.getCars().isEmpty()) {
            System.out.println("No cars found");
            return;
        } else System.out.println(carService.getCars());
        System.out.println("Enter the id of the car");
        int id = scanner.nextInt();
        System.out.println("Enter the year of the car");
        int year = scanner.nextInt();
        System.out.println("Enter the price of the car");
        double price = scanner.nextDouble();
        System.out.println("Enter the make of the car");
        String make = scanner.next();
        System.out.println("Enter the model of the car");
        String model = scanner.next();
        System.out.println("Enter the condition of the car");
        String condition = scanner.next();
        Car car = new Car(make, model, year, price, condition);
        carService.editCar(id, car);
        pauseBeforeExit();

    }

    /**
     * This method is an addition layer with scanner functionality
     * remove car from the system
     */
    public void removeCar() {
        if (carService.getCars().isEmpty()) {
            System.out.println("No cars found");
            return;
        } else System.out.println(carService.getCars());
        System.out.println("Enter the id of the car");
        int id = scanner.nextInt();
        carService.sellCar(id);
        pauseBeforeExit();
    }

}
