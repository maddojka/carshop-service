package com.soroko.carshop.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.soroko.carshop.annotations.Loggable;
import com.soroko.carshop.dto.CarDTO;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.mapper.CarMapper;
import com.soroko.carshop.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yuriy.soroko
 */
@Loggable
@WebServlet("/")
public class CarServlet extends HttpServlet {

    private final CarService carService;
    private final ObjectMapper objectMapper;
    private final CarMapper carMapper = CarMapper.INSTANCE;

    public CarServlet() throws SQLException, IOException {
        this.carService = new CarService();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/addcar":
                    addCar(request, response);
                    break;
                case "/deletecar":
                    deleteCar(request, response);
                    break;
                case "/editcar":
                    editCar(request, response);
                    break;
                case "/getcars":
                    getCars(request, response);
                    break;
                case "/getcar":
                    getCarById(request, response);
                    break;
                case "/getcarbyconditionandprice":
                    getCarByConditionAndPrice(request, response);
                    break;
                case "/getcarbycondition":
                    getCarByCondition(request, response);
                    break;
                case "/getcarbymake":
                    getCarByMake(request, response);
                    break;
                case "/getcarbymodel":
                    getCarByModel(request, response);
                    break;
                case "/getcarbyprice":
                    getCarByPrice(request, response);
                    break;
                case "/getcarbyyear":
                    getCarByYear(request, response);
                    break;
                default:
                    System.out.println("wrong request");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        }
    }

    public void getCars(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        List<Car> cars = carService.getCars();
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        request.setAttribute("cars", carsDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carsDTO));
    }

    public void addCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer year = Integer.valueOf(request.getParameter("year"));
        String condition = request.getParameter("condition");
        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setPrice(price);
        car.setYear(year);
        car.setCondition(condition);
        carService.addCar(car);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void editCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer year = Integer.valueOf(request.getParameter("year"));
        String condition = request.getParameter("condition");
        Car car = new Car();
        car.setId(id);
        car.setMake(make);
        car.setModel(model);
        car.setPrice(price);
        car.setYear(year);
        car.setCondition(condition);
        carService.editCar(car);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void deleteCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        carService.sellCar(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void getCarById(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carService.getCar(id);
        CarDTO carDTO = carMapper.toCarDTO(car);
        request.setAttribute("car", carDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carDTO));
    }

    public void getCarByConditionAndPrice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String condition = request.getParameter("condition");
        Double price = Double.parseDouble(request.getParameter("price"));
        List<Car> cars = carService.findByConditionAndPrice(condition, price);
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        request.setAttribute("cars", carsDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carsDTO));
    }

    public void getCarByCondition(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String condition = request.getParameter("condition");
        List<Car> cars = carService.findByCondition(condition);
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        request.setAttribute("cars", carsDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carsDTO));
    }

    public void getCarByMake(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String make = request.getParameter("make");
        List<Car> cars = carService.findAndSortBy(make);
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        request.setAttribute("cars", carsDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carsDTO));
    }

    public void getCarByModel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String model = request.getParameter("model");
        List<Car> cars = carService.findByModel(model);
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        request.setAttribute("cars", carsDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carsDTO));
    }

    public void getCarByPrice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Double price = Double.parseDouble(request.getParameter("price"));
        List<Car> cars = carService.findAndSortBy(price);
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        request.setAttribute("cars", carsDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carsDTO));
    }

    public void getCarByYear(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer year = Integer.valueOf(request.getParameter("year"));
        List<Car> cars = carService.findAndSortBy(year);
        List<CarDTO> carsDTO = carMapper.toCarDTOList(cars);
        request.setAttribute("cars", carsDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(carsDTO));
    }

}
