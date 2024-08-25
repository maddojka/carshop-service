package com.soroko.carshop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.logging.Level;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 * @version 1.0
 */
@Getter
@Setter
@Component
public class Car {

    private int id;

    private String make;

    private String model;

    private int year;

    private double price;

    private String condition;

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

    public void setMake(String make) {
        if ("".equals(make) || make == null) {
            LOGGER.log(Level.SEVERE, "Make cannot be empty");
            throw new IllegalArgumentException("Make cannot be empty");
        }
        this.make = make;
    }

    public void setModel(String model) {
        if ("".equals(model) || model == null) {
            LOGGER.log(Level.SEVERE, "Model cannot be empty");
            throw new IllegalArgumentException("Model cannot be empty");
        }
        this.model = model;
    }

    public void setYear(int year) {
        if (year < 2000 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            LOGGER.log(Level.SEVERE, "Year out of range");
            throw new IllegalArgumentException("Year out of range");
        }
        this.year = year;
    }

    public void setPrice(double price) {
        if (price < 0) {
            LOGGER.log(Level.SEVERE, "Price cannot be negative");
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void setCondition(String condition) {
        if ("".equals(condition) || condition == null) {
            LOGGER.log(Level.SEVERE, "Condition cannot be empty");
            throw new IllegalArgumentException("Condition cannot be empty");
        }
        this.condition = condition;
    }

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
