package com.soroko.carshop.servlet;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.service.CarService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CarServletTest extends Mockito {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private CarServlet servlet;
    private CarService carService;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servlet = new CarServlet();
        carService = Mockito.mock(CarService.class);
    }

    @Test
    @DisplayName("Check for getting correct list of cars from request")
    void shouldGetCars() throws IOException, SQLException {
        when(request.getRequestURI()).thenReturn("/getcars");
        List<Car> cars = new ArrayList<>() {{
            add(new Car("BMW", "X5", 2024, 12_000_000.0, "new"));
            add(new Car("Lada", "Granta", 2024, 2_000_000.0, "new"));
        }};
        when(carService.getCars()).thenReturn(cars);
        servlet.getCars(request, response);
        verify(request).setAttribute("cars", cars);
        verify(request, times(1));
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for adding correct car")
    void shouldAddNewCar() throws SQLException {
        when(request.getRequestURI()).thenReturn("/addcar");
        when(request.getParameter("make")).thenReturn("BMW");
        when(request.getParameter("model")).thenReturn("X5");
        when(request.getParameter("year")).thenReturn(String.valueOf(2024));
        when(request.getParameter("price")).thenReturn(String.valueOf(12_000_000.0));
        when(request.getParameter("condition")).thenReturn("new");
        servlet.addCar(request, response);
        verify(request, times(1)).getParameter("make");
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for editing existing car")
    void shouldEditCar() throws SQLException {
        when(request.getRequestURI()).thenReturn("/editcar");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("make")).thenReturn("BMW");
        when(request.getParameter("model")).thenReturn("X5");
        when(request.getParameter("year")).thenReturn(String.valueOf(2024));
        when(request.getParameter("price")).thenReturn(String.valueOf(12_000_000.0));
        when(request.getParameter("condition")).thenReturn("new");
        servlet.editCar(request, response);
        verify(request, times(1)).getParameter("make");
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for deleting existing car")
    void shouldDeleteCar() throws SQLException {
        when(request.getRequestURI()).thenReturn("/deletecar");
        when(request.getParameter("id")).thenReturn("1");
        servlet.deleteCar(request, response);
        verify(request, times(1)).getParameter("id");
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for getting car by id")
    void shouldGetCarById() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getcar");
        when(request.getParameter("id")).thenReturn("1");
        Car car = new Car("BMW", "X5", 2024, 12_000_000.0, "new");
        when(carService.getCar(1)).thenReturn(car);
        servlet.getCarById(request, response);
        verify(request).setAttribute("car", car);
        verify(request, times(1));
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for getting car by condition and price")
    void shouldGetCarByConditionAndPrice() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getcarbyconditionandprice");
        when(request.getParameter("condition")).thenReturn("new");
        when(request.getParameter("price")).thenReturn(String.valueOf(12_000_000.0));
        List<Car> cars = new ArrayList<>() {{
            add(new Car("BMW", "X5", 2024, 12_000_000.0, "new"));
            add(new Car("Lada", "Granta", 2024, 2_000_000.0, "new"));
        }};
        when(carService.findByConditionAndPrice("new", 3_000_000.0)).thenReturn(cars);
        servlet.getCarByConditionAndPrice(request, response);
        verify(request).setAttribute("cars", cars);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for getting car by condition")
    void shouldGetCarByCondition() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getcarbycondition");
        when(request.getParameter("condition")).thenReturn("new");
        List<Car> cars = new ArrayList<>() {{
            add(new Car("BMW", "X5", 2024, 12_000_000.0, "new"));
            add(new Car("Lada", "Granta", 2024, 2_000_000.0, "new"));
        }};
        when(carService.findByCondition("new")).thenReturn(cars);
        servlet.getCarByCondition(request, response);
        verify(request).setAttribute("cars", cars);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for getting car by make")
    void shouldGetCarByMake() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getcarbymake");
        when(request.getParameter("make")).thenReturn("Lada");
        List<Car> cars = new ArrayList<>() {{
            add(new Car("BMW", "X5", 2024, 12_000_000.0, "new"));
            add(new Car("Lada", "Granta", 2024, 2_000_000.0, "new"));
        }};
        when(carService.findAndSortBy("Lada")).thenReturn(cars);
        servlet.getCarByMake(request, response);
        verify(request).setAttribute("cars", cars);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for getting car by model")
    void shouldGetCarByModel() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getcarbymodel");
        when(request.getParameter("model")).thenReturn("X5");
        List<Car> cars = new ArrayList<>() {{
            add(new Car("BMW", "X5", 2024, 12_000_000.0, "new"));
            add(new Car("Lada", "Granta", 2024, 2_000_000.0, "new"));
        }};
        when(carService.findByModel("X5")).thenReturn(cars);
        servlet.getCarByModel(request, response);
        verify(request).setAttribute("cars", cars);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for getting car by price")
    void shouldGetCarByPrice() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getcarbyprice");
        when(request.getParameter("price")).thenReturn(String.valueOf(12_000_000.0));
        List<Car> cars = new ArrayList<>() {{
            add(new Car("BMW", "X5", 2024, 12_000_000.0, "new"));
            add(new Car("Lada", "Granta", 2024, 2_000_000.0, "new"));
        }};
        when(carService.findAndSortBy(2024)).thenReturn(cars);
        servlet.getCarByPrice(request, response);
        verify(request).setAttribute("cars", cars);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Check for getting car by year")
    void shouldGetCarByYear() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getcarbyyear");
        when(request.getParameter("year")).thenReturn(String.valueOf(2024));
        List<Car> cars = new ArrayList<>() {{
            add(new Car("BMW", "X5", 2024, 12_000_000.0, "new"));
            add(new Car("Lada", "Granta", 2024, 2_000_000.0, "new"));
        }};
        when(carService.findAndSortBy(5_000_000.0)).thenReturn(cars);
        servlet.getCarByYear(request, response);
        verify(request).setAttribute("cars", cars);
        Assertions.assertEquals(200, response.getStatus());
    }
}