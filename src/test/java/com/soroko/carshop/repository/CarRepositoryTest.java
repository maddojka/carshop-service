package com.soroko.carshop.repository;

import com.soroko.carshop.entity.Car;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
class CarRepositoryTest {

    private PostgresTest postgresTest;
    private CarRepository carRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private Car car;


    @BeforeEach
    void setUp() throws SQLException, IOException {
        postgresTest = new PostgresTest();
        carRepository = new CarRepository();
        orderRepository = new OrderRepository();
        userRepository = new UserRepository();
        orderRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        car = new Car("Volksvagen", "Polo", 2020, 2_000_000.0, "used");
        carRepository.insert(car);
    }

    @Test
    @DisplayName("Checking get all cars method - shouldn't be empty")
    void test_shouldGetAllCars() throws SQLException {
        assertFalse(carRepository.getAllCars().isEmpty());
    }

    @Test
    @DisplayName("Checking car insertion method - should insert car")
    void test_shouldInsertCar() throws SQLException {
        Car retreivedCar = carRepository.findById(car.getId());
        Assertions.assertEquals(car, retreivedCar);
    }

    @Test
    @DisplayName("Checking car updating - should update car")
    void test_shouldUpdateCar() throws SQLException {
        Car updatedCar = new Car("Volksvagen", "Tiguan", 2020, 2_000_000.0, "used");
        carRepository.update(updatedCar);
        Car retrievedCar = carRepository.getAllCars().get(0);
        Assertions.assertEquals(updatedCar, retrievedCar);
    }

    @Test
    @DisplayName("Checking car deleting - should delete car")
    void test_shouldDeleteCarById() throws SQLException {
        Car retrievedCar = carRepository.getAllCars().get(0);
        carRepository.deleteById(retrievedCar.getId());
        assertTrue(carRepository.getAllCars().isEmpty());
    }

    @Test
    @DisplayName("Checking car finding - should find car by car id")
    void test_shouldFindCarById() throws SQLException {
        Car retrievedCar = carRepository.findById(1);
        Assertions.assertTrue(retrievedCar != null);
    }
}