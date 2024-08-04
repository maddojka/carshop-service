package com.soroko.carshop.entity;

import java.util.Calendar;
import java.util.logging.Level;

import static com.soroko.carshop.logger.CarShopLogger.LOGGER;


/**
 * @author yuriy.soroko
 */
public class Car {
    public static int CAR_COUNT;
    private int carId;
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
        this.carId = CAR_COUNT++;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.condition = condition;
    }

    public Car() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        if ("".equals(make) || make == null) {
            LOGGER.log(Level.SEVERE, "Make cannot be empty");
            throw new IllegalArgumentException("Make cannot be empty");
        }
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if ("".equals(model) || model == null) {
            LOGGER.log(Level.SEVERE, "Model cannot be empty");
            throw new IllegalArgumentException("Model cannot be empty");
        }
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 2000 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            LOGGER.log(Level.SEVERE, "Year out of range");
            throw new IllegalArgumentException("Year out of range");
        }
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            LOGGER.log(Level.SEVERE, "Price cannot be negative");
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public String getCondition() {
        return condition;
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
                "carId=" + carId +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                '}';
    }
}
