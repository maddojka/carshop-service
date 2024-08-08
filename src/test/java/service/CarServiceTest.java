package service;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarServiceTest {

    private CarService carService;

    @BeforeEach
    public void setUp() {
        carService = new CarService();
    }

    @Test
    @DisplayName("Check add car method - car is null")
    public void addCar_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.addCar(null));
    }

    @Test
    @DisplayName("Check add car method - car is not null")
    public void addCar_isNotNull() {
        Car car = new Car();
        carService.addCar(car);
    }

    @Test
    @DisplayName("Check get car method - car id is negative")
    public void getCar_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.getCar(-1));
    }

    @Test
    @DisplayName("Check get car method - car id is oversized")
    public void getCar_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.getCar(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Check get car method - car id is OK")
    public void getCar_correctId() {
        Car car = new Car();
        carService.getCars().add(car);
        carService.getCar(0);
    }

    @Test
    @DisplayName("Check edit car method - car id is negative")
    public void editCar_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.editCar(-1, new Car()));
    }

    @Test
    @DisplayName("Check edit car method - car id is oversized")
    public void editCar_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.editCar(Integer.MAX_VALUE, new Car()));
    }

    @Test
    @DisplayName("Check edit car method - car id is OK")
    public void editCar_correctId() {
        Car car =
                new Car("Lada", "Granta", 2010, 1_000_000, "new");
        carService.getCars().add(car);
        carService.editCar(0, car);
    }

    @Test
    @DisplayName("Check sell car method - car id is negative")
    public void sellCar_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.sellCar(-1));
    }

    @Test
    @DisplayName("Check sell car method - car id is oversized")
    public void sellCar_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.sellCar(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Check sell car method - car id is OK")
    public void sellCar_correctId() {
        Car car =
                new Car("Lada", "Granta", 2010, 1_000_000, "new");
        carService.getCars().add(car);
        carService.sellCar(0);
    }

    @Test
    @DisplayName("Check find and sort car by make method - make is empty")
    public void findAndSortCarByMake_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(""));

    }

    @Test
    @DisplayName("Check find and sort car by make - make is null")
    public void findAndSortCarByMake_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(null));

    }

    @Test
    @DisplayName("Check find and sort car by make - make is OK")
    public void findAndSortCarByMake_isOk() {
        carService.findByModel("Lada");
    }

    @Test
    @DisplayName("Check find car by model method - model is empty")
    public void findCarByModel_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByModel(""));

    }

    @Test
    @DisplayName("Check find car by model method - model is null")
    public void findCarByModel_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByModel(null));

    }

    @Test
    @DisplayName("Check find car by model method - model is OK")
    public void findACarByModel_isOk() {
        carService.findByModel("Granta");
    }

    @Test
    @DisplayName("Check find and sort car by year method - year is less than 2000")
    public void findAndSortCarByYear_isLessThan2000() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(1999));

    }

    @Test
    @DisplayName("Check find and sort car by year method - year is more than current")
    public void findAndSortCarByYear_isMoreThanCurrent() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(2025));

    }

    @Test
    @DisplayName("Check find and sort car by year method - year is OK")
    public void findAndSortCarByYear_isOk() {
        Car car =
                new Car("Lada", "Granta", 2024, 1_000_000, "new");
        carService.getCars().add(car);
        carService.findAndSortBy(2024);
    }

    @Test
    @DisplayName("Check find and sort cat by price method - price is negative")
    public void findAndSortCarByPrice_isNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(-1.0));

    }

    @Test
    @DisplayName("Check find and sorth car by price method - price is OK")
    public void findAndSortCarByPrice_isOk() {
        Car car =
                new Car("Lada", "Granta", 2024, 1_000_000, "new");
        carService.getCars().add(car);
        carService.findAndSortBy(1_000_000.0);
    }

    @Test
    @DisplayName("Check find and sort car by condition method - condition is empty")
    public void findAndSortCarByCondition_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByCondition(""));

    }

    @Test
    @DisplayName("Check find and sort car by condition method - condition is null")
    public void findAndSortCarByCondition_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByCondition(null));

    }

    @Test
    @DisplayName("Check find and sort car by condition method - condition is OK")
    public void findAndSortCarByCondition_isOk() {
        carService.findByCondition("new");
    }

    @Test
    @DisplayName("Check find car by condition and price method - condition is empty")
    public void findCarByConditionAndPrice_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByConditionAndPrice("", 1_000_000.0));

    }

    @Test
    @DisplayName("Check find car by condition and price method - condition is null")
    public void findCarByConditionAndPrice_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByConditionAndPrice(null, 1_000_000.0));

    }

    @Test
    @DisplayName("Check find car by condition and price method - condition is negative")
    public void findCarByConditionAndPrice_isNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByConditionAndPrice("new", -1));

    }

    @Test
    @DisplayName("Check find car by condition and price method - price is OK")
    public void findCarByConditionAndPrice_isOk() {
        carService.findByConditionAndPrice("new", 1_000_000.0);
    }
}
