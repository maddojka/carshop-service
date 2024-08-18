package com.soroko.carshop.servlet;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class OrderServletTest extends Mockito {
    HttpServletRequest request;
    HttpServletResponse response;
    OrderServlet servlet;
    OrderService orderService;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servlet = new OrderServlet();
    }

    @Test
    @DisplayName("Check for getting correct list of orders from request")
    void shouldGetOrders() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getorders");
        servlet.getOrders(request, response);
        verify(request, times(1));
        List<Order> orders = new ArrayList<>() {{
            add(new Order());
            add(new Order());
        }};
        when(orderService.getOrders()).thenReturn(orders);
        servlet.getOrders(request, response);
        verify(request).setAttribute("orders", orders);
        verify(request, times(1));
    }

    @Test
    @DisplayName("Check for adding correct order")
    void shouldAddNewOrder() throws SQLException {
        when(request.getRequestURI()).thenReturn("/addorder");
        when(request.getParameter("status")).thenReturn("CREATED");
        servlet.addOrder(request, response);
        verify(request, times(1)).getParameter("status");
    }

    @Test
    @DisplayName("Check for editing existing order")
    void shouldEditOrder() throws SQLException {
        when(request.getRequestURI()).thenReturn("/editorder");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("status")).thenReturn("CREATED");
        servlet.editOrder(request, response);
        verify(request, times(1)).getParameter("status");
    }

    @Test
    @DisplayName("Check for deleting existing order")
    void shouldDeleteOrder() throws SQLException {
        when(request.getRequestURI()).thenReturn("/deleteorder");
        when(request.getParameter("id")).thenReturn("1");
        servlet.deleteOrder(request, response);
        verify(request, times(1)).getParameter("id");
    }

    @Test
    @DisplayName("Check for getting order by id")
    void shouldGetOrderById() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getorder");
        when(request.getParameter("id")).thenReturn("1");
        Order order = new Order();
        when(orderService.getOrder(1)).thenReturn(order);
        servlet.getOrderById(request, response);
        verify(request).setAttribute("order", order);
        verify(request, times(1));
    }

    @Test
    @DisplayName("Check for completing order by id")
    void shouldCompleteOrderById() throws SQLException {
        when(request.getRequestURI()).thenReturn("/completeorder");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("status")).thenReturn("COMPLETED");
        servlet.completeOrder(request, response);
        verify(request, times(1)).getParameter("id");
    }

    @Test
    @DisplayName("Check for getting order by date")
    void shouldGetOrderByDate() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getorderbydate");
        when(request.getParameter("date")).thenReturn(String.valueOf(LocalDate.of(2018, 05, 04)));
        List<Order> orders = new ArrayList<>() {{
            add(new Order());
            add(new Order());
        }};
        when(orderService.findBy(LocalDate.now())).thenReturn(orders);
        servlet.getOrderByDate(request, response);
        verify(request).setAttribute("orders", orders);
        verify(request, times(1)).getParameter("date");
    }

    @Test
    @DisplayName("Check for getting order by status")
    void shouldGetOrderByStatus() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getorderbystatus");
        when(request.getParameter("status")).thenReturn("CREATED");
        List<Order> orders = new ArrayList<>() {{
            add(new Order());
            add(new Order());
        }};
        when(orderService.findBy("CREATED")).thenReturn(orders);
        servlet.getOrderByStatus(request, response);
        verify(request).setAttribute("orders", orders);
        verify(request, times(1)).getParameter("status");
    }
}