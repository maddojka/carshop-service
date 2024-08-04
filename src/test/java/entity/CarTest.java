package entity;

import com.soroko.carshop.entity.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarTest {

    @Test
    public void Car_empty_make() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("", "Granta", 2024, 2_000_000.0, "new");
        });
    }

    @Test
    public void Car_get_make() {
        Car car = new Car("Lada", "Granta", 2024, 2_000_000.0, "new");
        car.getMake();
        car.toString();
    }

    @Test
    public void Car_set_make() {
        Car car = new Car("Lada", "Granta", 2024, 2_000_000.0, "new");
        car.setMake("Audi");
    }

    @Test
    public void Car_empty_model() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", "", 2024, 2_000_000.0, "new");
        });

    }

    @Test
    public void Car_less_than2000_year() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", "Granta", 1999, 2_000_000.0, "new");
        });
    }

    @Test
    public void Car_future_year() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", "Granta", 2025, 2_000_000.0, "new");
        });
    }

    @Test
    public void Car_negative_price() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", "Granta", 2024, -5.0, "new");
        });
    }

    @Test
    public void Car_empty_price() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", "Granta", 2024, -5.0, "new");
        });
    }

    @Test
    public void Car_empty_condition() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", "Granta", 2024, 2_000_000.0, "");
        });
    }

    @Test
    public void Car_null_make() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car(null, "Granta", 2024, 2_000_000.0, "");
        });
    }

    @Test
    public void Car_null_model() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", null, 2024, 2_000_000.0, "");
        });
    }

    @Test
    public void Car_null_condition() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Car car = new Car("Lada", "Granta", 2024, 2_000_000.0, null);
        });
    }
}
