package service;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 */
public class CarServiceTest {

    private CarService carService;

    @BeforeEach
    public void setUp() {
        carService = new CarService();
    }

    @Test
    public void addCar_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.addCar(null));
    }

    @Test
    public void addCar_isNotNull() {
        Car car = new Car();
        carService.addCar(car);
    }

    @Test
    public void getCar_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.getCar(-1));
    }

    @Test
    public void getCar_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.getCar(Integer.MAX_VALUE));
    }

    @Test
    public void getCar_correctId() {
        Car car = new Car();
        carService.getCars().add(car);
        carService.getCar(0);
    }

    @Test
    public void editCar_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.editCar(-1, new Car()));
    }

    @Test
    public void editCar_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.editCar(Integer.MAX_VALUE, new Car()));
    }

    @Test
    public void editCar_correctId() {
        Car car =
                new Car("Lada", "Granta", 2010, 1_000_000, "new");
        carService.getCars().add(car);
        carService.editCar(0, car);
    }

    @Test
    public void sellCar_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.sellCar(-1));
    }

    @Test
    public void sellCar_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.sellCar(Integer.MAX_VALUE));
    }

    @Test
    public void sellCar_correctId() {
        Car car =
                new Car("Lada", "Granta", 2010, 1_000_000, "new");
        carService.getCars().add(car);
        carService.sellCar(0);
    }

    @Test
    public void findAndSortCarByMake_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(""));

    }

    @Test
    public void findAndSortCarByMake_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(null));

    }

    @Test
    public void findAndSortCarByMake_isOk() {
                carService.findByModel("Lada");
    }

    @Test
    public void findCarByModel_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByModel(""));

    }

    @Test
    public void findCarByModel_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByModel(null));

    }

    @Test
    public void findACarByModel_isOk() {
        carService.findByModel("Granta");
    }

    @Test
    public void findAndSortCarByYear_isLessThan2000() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(1999));

    }

    @Test
    public void findAndSortCarByYear_isMoreThanCurrent() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(2025));

    }

    @Test
    public void findAndSortCarByYear_isOk() {
        Car car =
                new Car("Lada", "Granta", 2024, 1_000_000, "new");
        carService.getCars().add(car);
        carService.findAndSortBy(2024);
    }

    @Test
    public void findAndSortCarByPrice_isNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findAndSortBy(-1.0));

    }

    @Test
    public void findAndSortCarByPrice_isOk() {
        Car car =
                new Car("Lada", "Granta", 2024, 1_000_000, "new");
        carService.getCars().add(car);
        carService.findAndSortBy(1_000_000.0);
    }

    @Test
    public void findAndSortCarByCondition_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByCondition(""));

    }

    @Test
    public void findAndSortCarByCondition_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByCondition(null));

    }

    @Test
    public void findAndSortCarByCondition_isOk() {
        carService.findByCondition("new");
    }

    @Test
    public void findCarByConditionAndPrice_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByConditionAndPrice("", 1_000_000.0));

    }

    @Test
    public void findCarByConditionAndPrice_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByConditionAndPrice(null, 1_000_000.0));

    }

    @Test
    public void findCarByConditionAndPrice_isNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                carService.findByConditionAndPrice("new", -1));

    }

    @Test
    public void findCarByConditionAndPrice_isOk() {
        carService.findByConditionAndPrice("new", 1_000_000.0);
    }



}
