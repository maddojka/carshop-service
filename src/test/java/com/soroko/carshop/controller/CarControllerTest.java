package com.soroko.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarController controller;

    @InjectMocks
    private CarService carService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    @DisplayName("Check for getting correct list of cars from request")
    void test_canGetAllCars() throws Exception {
        mockMvc.perform(get("/api/car/all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting car by id")
    void test_CanGetCarById() throws Exception {
        mockMvc.perform(get("/api/car/get?id=1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for adding correct car")
    void test_canAddCar() throws Exception {
        Car car = new Car("Lada", "Vesta", 2024, 2_000_000.0, "new");
        car.setId(1);
        String carJson = objectMapper.writeValueAsString(car);
        mockMvc.perform(post("/api/car/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for editing existing car")
    void test_canEditCar() throws Exception {
        Car car = new Car("Lada", "Vesta", 2024, 2_000_000.0, "new");
        car.setId(1);
        String carJson = objectMapper.writeValueAsString(car);
        mockMvc.perform(patch("/api/car/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for deleting existing car")
    void test_canDeleteCar() throws Exception {
        String carJson = objectMapper.writeValueAsString(1);
        mockMvc.perform(delete("/api/car/delete?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting car by condition and price")
    void test_canGetCarByConditionAndPrice() throws Exception {
        mockMvc.perform(get("/api/car/getbyconditionandprice?condition=used&price=1000000"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting car by condition")
    void test_canGetCarByCondition() throws Exception {
        mockMvc.perform(get("/api/car/getbycondition?condition=new")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting car by make")
    void test_canGetCarByMake() throws Exception {
        mockMvc.perform(get("/api/car/getbymake?make=Lada")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting car by model")
    void test_canGetCarByModel() throws Exception {
        mockMvc.perform(get("/api/car/getbymodel?model=Granta")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting car by price")
    void test_canGetCarByPrice() throws Exception {
        mockMvc.perform(get("/api/car/getbyprice?price=2000000")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting car by year")
    void test_canGetCarByYear() throws Exception {
        mockMvc.perform(get("/api/car/getbyyear?year=2024")).andExpect(status().isOk());
    }
}