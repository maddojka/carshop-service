package com.soroko.carshop.repository;

import com.soroko.carshop.entity.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class CarRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine");
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    private Car car;


    @BeforeEach
    void setUp() throws SQLException {
        orderRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        car = new Car("Volksvagen", "Polo", 2020, 2_000_000.0, "used");
        carRepository.save(car);
    }

    @Test
    @DisplayName("Checking get all cars method - shouldn't be empty")
    void test_shouldFindAll() throws SQLException {
        assertFalse(carRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking car insertion method - should insert car")
    void test_shouldSaveCar() throws SQLException {
        Car retreivedCar = carRepository.getById(car.getId());
        Assertions.assertEquals(car, retreivedCar);
    }

    @Test
    @DisplayName("Checking car updating - should update car")
    void test_shouldUpdateCar() throws SQLException {
        Car updatedCar = new Car("Volksvagen", "Tiguan", 2020, 2_000_000.0, "used");
        carRepository.save(updatedCar);
        Car retrievedCar = carRepository.findAll().get(0);
        Assertions.assertEquals(updatedCar, retrievedCar);
    }

    @Test
    @DisplayName("Checking car deleting - should delete car")
    void test_shouldDeleteCarById() throws SQLException {
        Car retrievedCar = carRepository.findAll().get(0);
        carRepository.deleteById(retrievedCar.getId());
        assertTrue(carRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking car finding - should find car by car id")
    void test_shouldFindCarById() throws SQLException {
        Car retrievedCar = carRepository.getById(1);
        Assertions.assertTrue(retrievedCar != null);
    }
}