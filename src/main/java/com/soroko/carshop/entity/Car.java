package com.soroko.carshop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;


/**
 * This class consists fields to store car data to database
 *
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@Getter
@Setter
@Component
public class Car {
    /**
     * Basic fields of the car
     */
    private int id;
    private String make;
    private String model;
    private int year;
    private double price;
    private String condition;

    /**
     * Constructor which required to create a car
     * @param make make of the car
     * @param model model of the car
     * @param year year of the car
     * @param price price of the car
     * @param condition condition of the car
     */
    public Car(String make, String model, int year, double price, String condition) {
        setMake(make);
        setModel(model);
        setYear(year);
        setPrice(price);
        setCondition(condition);
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.condition = condition;
    }

    public Car() {
    }

    // getters and setters
    public void setMake(String make) {
        if ("".equals(make) || make == null) {
            log.info("Make cannot be empty");
            throw new IllegalArgumentException("Make cannot be empty");
        }
        this.make = make;
    }

    public void setModel(String model) {
        if ("".equals(model) || model == null) {
            log.info("Model cannot be empty");
            throw new IllegalArgumentException("Model cannot be empty");
        }
        this.model = model;
    }

    public void setYear(int year) {
        if (year < 2000 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            log.info("Year out of range");
            throw new IllegalArgumentException("Year out of range");
        }
        this.year = year;
    }

    public void setPrice(double price) {
        if (price < 0) {
            log.info("Price cannot be negative");
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setCondition(String condition) {
        if ("".equals(condition) || condition == null) {
            log.info("Condition cannot be empty");
            throw new IllegalArgumentException("Condition cannot be empty");
        }
        this.condition = condition;
    }

    /**
     * Text representation of the car object
     * @return returns String
     */
    @Override
    public String toString() {
        return "Car{" +
                "carId=" +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                '}';
    }
}