package com.soroko.carshop.servlet;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class UserServletTest extends Mockito {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private UserServlet servlet;
    private UserService userService;

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servlet = new UserServlet();
        userService = Mockito.mock(UserService.class);
    }

    @Test
    @DisplayName("Check for getting correct list of users from request")
    void shouldGetUsers() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getusers");
        servlet.getUsers(request, response);
        List<User> users = new ArrayList<>() {{
            add(
                    new User("John", "123456", "john@gmail.com",
                            10, User.Role.CLIENT));
        }};
        when(userService.getUsers()).thenReturn(users);
        servlet.getUsers(request, response);
        verify(request).setAttribute("users", users);
        verify(request, times(1));
    }

    @Test
    @DisplayName("Check for adding correct user")
    void shouldAddNewUser() throws SQLException {
        when(request.getRequestURI()).thenReturn("/adduser");
        when(request.getParameter("username")).thenReturn("John");
        when(request.getParameter("password")).thenReturn("123456");
        when(request.getParameter("email")).thenReturn("john@gmail.com");
        when(request.getParameter("numberOfPurchases")).thenReturn("10");
        when(request.getParameter("role")).thenReturn("CLIENT");
        servlet.addUser(request, response);
        verify(request, times(1)).getParameter("username");
    }

    @Test
    @DisplayName("Check for editing existing user")
    void shouldEditUser() throws SQLException {
        when(request.getRequestURI()).thenReturn("/edituser");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("username")).thenReturn("John");
        when(request.getParameter("password")).thenReturn("123456");
        when(request.getParameter("email")).thenReturn("john@gmail.com");
        when(request.getParameter("numberOfPurchases")).thenReturn("10");
        when(request.getParameter("role")).thenReturn("CLIENT");
        servlet.editUser(request, response);
        verify(request, times(1)).getParameter("make");
    }

    @Test
    @DisplayName("Check for deleting existing user")
    void shouldDeleteUser() throws SQLException {
        when(request.getRequestURI()).thenReturn("/deleteuser");
        when(request.getParameter("id")).thenReturn("1");
        servlet.removeUser(request, response);
        verify(request, times(1)).getParameter("id");
    }

    @Test
    @DisplayName("Check for getting user by id")
    void shouldGetUserById() throws SQLException, IOException {
        when(request.getRequestURI()).thenReturn("/getuser");
        when(request.getParameter("id")).thenReturn("1");
        servlet.getUserById(request, response);
        verify(request, times(1)).getParameter("id");
        User user = new User("John", "123456", "john@gmail.com",
                10, User.Role.CLIENT);
        when(userService.getUser(1)).thenReturn(user);
        servlet.getUserById(request, response);
        verify(request).setAttribute("user", user);
        verify(request, times(1));
    }
}