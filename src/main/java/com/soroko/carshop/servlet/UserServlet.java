package com.soroko.carshop.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.soroko.carshop.annotations.Loggable;
import com.soroko.carshop.dto.UserDTO;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.mapper.UserMapper;
import com.soroko.carshop.service.UserService;
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
 * @version 1.0
 */
@Loggable
@WebServlet("/")
public class UserServlet extends HttpServlet {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserServlet() throws SQLException, IOException {
        this.userService = new UserService();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/adduser":
                    addUser(request, response);
                    break;
                case "/deleteuser":
                    removeUser(request, response);
                    break;
                case "/edituser":
                    editUser(request, response);
                    break;
                case "/getusers":
                    getUsers(request, response);
                    break;
                case "/getuser":
                    getUserById(request, response);
                default:
                    System.out.println("wrong request");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        }
    }

    public void getUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        List<User> users = userService.getUsers();
        List<UserDTO> usersDTO = userMapper.toUserDTOList(users);
        request.setAttribute("users", usersDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(usersDTO));
    }

    public void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        int numberOfPurchases = Integer.parseInt(request.getParameter("numberOfpurchases"));
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
        user.setNumberOfpurchases(numberOfPurchases);
        userService.addUser(user);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void editUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("password");
        String role = request.getParameter("role");
        int numberOfpurchases = Integer.parseInt(request.getParameter("numberOfpurchases"));
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
        user.setNumberOfpurchases(numberOfpurchases);
        userService.editUser(user);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void removeUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.removeUser(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void getUserById(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUser(id);
        UserDTO userDTO = userMapper.toUserDTO(user);
        request.setAttribute("user", userDTO);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsBytes(userDTO));
    }
}
