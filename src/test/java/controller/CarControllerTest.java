package controller;

import com.soroko.carshop.controller.CarController;
import com.soroko.carshop.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author yuriy.soroko
 */
public class CarControllerTest {
    private CarController carController;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carController = new CarController();
        carService = new CarService();
    }



    /*@Test
    public void pauseBeforeExit_input() {
        String input = "something";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Assertions.assertEquals("something", carAction.pauseBeforeExit());

    }*/

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
    public void getCars_isNotNull() {
       /* Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.getCars(null);
        });*/
        CarController.carServiceIsNull(new CarService());
    }

    @Test
    public void getCarsByMake_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.getCarByMake(null);
        });
    }

    @Test
    public void getCarsByModel_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.getCarByModel(null);
        });
    }

    @Test
    public void getCarsByYear_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.getCarByYear(null);
        });
    }

    @Test
    public void getCarsByPrice_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.getCarByPrice(null);
        });
    }

    @Test
    public void getCarsByCondition_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.getCarByCondition(null);
        });
    }

    @Test
    public void getCarsByConditionAndPrice_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.getCarByConditionAndPrice(null);
        });
    }

    @Test
    public void registerCar_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.registerCar(null);
        });
    }

    @Test
    public void editCar_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.editCar(null);
        });
    }

    @Test
    public void removeCar_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            carController.removeCar(null);
        });
    }


}
