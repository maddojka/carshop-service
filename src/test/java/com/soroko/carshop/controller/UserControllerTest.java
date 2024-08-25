package com.soroko.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.UserService;
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
class UserControllerTest {

    @Mock
    private OrderController controller;

    @InjectMocks
    private UserService userService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Check for getting correct list of users from request")
    void test_canGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/user/all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting user by id")
    void test_canGetUserById() throws Exception {
        mockMvc.perform(get("/api/user/1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for adding correct user")
    void test_canAddUser() throws Exception {
        User user = new User(
                "admin", "123", "admin@mail.ru", 10, User.Role.ADMINISTRATOR);
        user.setId(1);
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/api/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for editing existing user")
    void test_canEditUser() throws Exception {
        User user = new User(
                "admin", "123", "admin@mail.ru", 10, User.Role.ADMINISTRATOR);
        user.setId(1);
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(patch("/api/user/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for deleting existing user")
    void test_canDeleteUser() throws Exception {
        String userJson = objectMapper.writeValueAsString(1);
        mockMvc.perform(delete("/api/user/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }
}