package controller;

import com.soroko.carshop.controller.CarController;
import com.soroko.carshop.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarControllerTest {
    private CarController carController;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carController = new CarController();
        carService = new CarService();
    }

    @Test
    public void Car_service_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CarController.carServiceIsNull(null);
        });
    }

    @Test
    public void Car_service_isNotNull() {
        CarController.carServiceIsNull(new CarService());
    }


    @Test
    public void Car_pauseBeforeExit_isOk() {

        InputStream in = new ByteArrayInputStream("something".getBytes());
        System.setIn(in);
        Assertions.assertEquals("something", carController.pauseBeforeExit());
    }
}
