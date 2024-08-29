package com.soroko.carshop.controller;

import com.soroko.carshop.annotations.Loggable;
import com.soroko.carshop.dto.UserDTO;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.mapper.UserMapper;
import com.soroko.carshop.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * This class consists REST API logic of users
 * @author yuriy.soroko
 */
@Loggable
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Get all users from the service
     *
     * @return returns list of all modified users by mapper
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() throws SQLException {
        List<User> users = userService.getUsers();
        List<UserDTO> usersDTO = userMapper.toUserDTOList(users);
        return ResponseEntity.ok(usersDTO);
    }

    /**
     * Get user by id
     *
     * @param id id of user
     * @return returns user data
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) throws SQLException {
        var user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        UserDTO userDTO = userMapper.toUserDTO(user);
        return ResponseEntity.ok(userDTO);

    }

    /**
     * Add user to the system
     *
     * @param user user you need to add in the system
     * @return returns user which was added
     */
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) throws SQLException {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    /**
     * @param user user you need to edit in the system
     * @return returns user which was edited
     */
    @PatchMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) throws SQLException {
        userService.editUser(user);
        return ResponseEntity.ok(user);
    }

    /**
     * @param id id of the user that need to delete from the system
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) throws SQLException {
        userService.removeUser(id);
    }
}
