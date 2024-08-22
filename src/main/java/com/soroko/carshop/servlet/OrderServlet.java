package com.soroko.carshop.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.soroko.carshop.annotations.Loggable;
import com.soroko.carshop.dto.OrderDTO;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.mapper.OrderMapper;
import com.soroko.carshop.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
@Loggable
@WebServlet("/")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    public OrderServlet() throws SQLException, IOException {
        this.orderService = new OrderService();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/addorder":
                    addOrder(request, response);
                    break;
                case "/deleteorder":
                    deleteOrder(request, response);
                    break;
                case "/editorder":
                    editOrder(request, response);
                    break;
                case "/getorders":
                    getOrders(request, response);
                    break;
                case "/getorder":
                    getOrderById(request, response);
                    break;
                case "/completeorder":
                    completeOrder(request, response);
                    break;
                case "/getorderbydate":
                    getOrderByDate(request, response);
                    break;
                case "/getorderbystatus":
                    getOrderByStatus(request, response);
                    break;
                default:
                    System.out.println("wrong request");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        }
    }

    public void getOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        List<Order> orders = orderService.getOrders();
        List<OrderDTO> ordersDTO = orderMapper.toOrderDTOList(orders);
        request.setAttribute("orders", ordersDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(ordersDTO));
    }

    public void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int carId = Integer.parseInt(request.getParameter("id"));
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer year = Integer.valueOf(request.getParameter("year"));
        String condition = request.getParameter("condition");
        Car car = new Car();
        car.setId(carId);
        car.setMake(make);
        car.setModel(model);
        car.setPrice(price);
        car.setYear(year);
        car.setCondition(condition);
        int userId = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("password");
        String role = request.getParameter("role");
        int numberOfpurchases = Integer.parseInt(request.getParameter("numberOfPurchases"));
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
        user.setNumberOfpurchases(numberOfpurchases);
        Order order = new Order();
        order.setCar(car);
        order.setUser(user);
        order.setStatus(Order.Status.CREATED);
        orderService.addOrder(order);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void editOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int carId = Integer.parseInt(request.getParameter("id"));
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer year = Integer.valueOf(request.getParameter("year"));
        String condition = request.getParameter("condition");
        Car car = new Car();
        car.setId(carId);
        car.setMake(make);
        car.setModel(model);
        car.setPrice(price);
        car.setYear(year);
        car.setCondition(condition);
        int userId = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("password");
        String role = request.getParameter("role");
        int numberOfpurchases = Integer.parseInt(request.getParameter("numberOfPurchases"));
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
        user.setNumberOfpurchases(numberOfpurchases);
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrder(id);
        order.setCar(car);
        order.setUser(user);
        order.setStatus(Order.Status.CREATED);
        orderService.editOrder(order);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void completeOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrder(id);
        order.setStatus(Order.Status.COMPLETED);
        orderService.editOrder(order);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.cancelOrder(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void getOrderById(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrder(id);
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);
        request.setAttribute("order", orderDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(orderDTO));
    }

    public void getOrderByDate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        LocalDate localDate = LocalDate.ofEpochDay(request.getDateHeader("createdAt"));
        List<Order> orders = orderService.findBy(localDate);
        List<OrderDTO> ordersDTO = orderMapper.toOrderDTOList(orders);
        request.setAttribute("orders", ordersDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(ordersDTO));
    }

    public void getOrderByStatus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String status = request.getParameter("status");
        List<Order> orders = orderService.findBy(status);
        List<OrderDTO> ordersDTO = orderMapper.toOrderDTOList(orders);
        request.setAttribute("orders", ordersDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(ordersDTO));
    }
}
