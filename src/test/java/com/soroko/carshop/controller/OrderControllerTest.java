package com.soroko.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.OrderService;
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
class OrderControllerTest {

    @Mock
    private OrderController controller;

    @InjectMocks
    private OrderService orderService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Check for getting correct list of orders from request")
    void test_canGetAllOrders() throws Exception {
        mockMvc.perform(get("/api/order/all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting order by id")
    void test_canGetOrderById() throws Exception {
        mockMvc.perform(get("/api/order/{id}")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for adding correct order")
    void test_canAddOrder() throws Exception {
        Car car = new Car("Lada", "Vesta", 2024, 2_000_000.0, "new");
        User user = new User(
                "admin", "123", "admin@mail.ru", 10, User.Role.ADMINISTRATOR);
        Order order = new Order(user, car, Order.Status.CREATED);
        car.setId(1);
        user.setId(1);
        order.setId(1);
        String orderJson = objectMapper.writeValueAsString(order);
        mockMvc.perform(post("/api/order/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for editing existing order")
    void test_canEditOrder() throws Exception {
        Car car = new Car("Lada", "Vesta", 2024, 2_000_000.0, "new");
        User user = new User(
                "admin", "123", "admin@mail.ru", 10, User.Role.ADMINISTRATOR);
        Order order = new Order(user, car, Order.Status.CREATED);
        car.setId(1);
        user.setId(1);
        order.setId(1);
        String orderJson = objectMapper.writeValueAsString(order);
        mockMvc.perform(post("/api/order/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for deleting existing order")
    void test_canDeleteOrder() throws Exception {
        String orderJson = objectMapper.writeValueAsString(1);
        mockMvc.perform(delete("/api/car/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for completing order by id")
    void test_canCompleteOrder() throws Exception {
        Car car = new Car("Lada", "Vesta", 2024, 2_000_000.0, "new");
        User user = new User(
                "admin", "123", "admin@mail.ru", 10, User.Role.ADMINISTRATOR);
        Order order = new Order(user, car, Order.Status.CREATED);
        car.setId(1);
        user.setId(1);
        order.setId(1);
        String orderJson = objectMapper.writeValueAsString(order);
        mockMvc.perform(post("/api/order/complete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting order by date")
    void test_canGetOrderByDate() throws Exception {
        mockMvc.perform(get("/api/order/getbydate")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting order by status")
    void test_canGetOrderByStatus() throws Exception {
        mockMvc.perform(get("/api/order/getbystatus")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting order by user")
    void test_canGetOrderByUser() throws Exception {
        mockMvc.perform(get("/api/order/getbyuser")).andExpect(status().isOk());
    }
}