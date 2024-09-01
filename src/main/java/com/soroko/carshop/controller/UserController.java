package com.soroko.carshop.controller;

import com.soroko.auditstarter.annotations.EnableLoggable;
import com.soroko.carshop.dto.UserDTO;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.mapper.UserMapper;
import com.soroko.carshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * This class consists REST API logic of users
 *
 * @author yuriy.soroko
 */
@Slf4j
@EnableLoggable
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(
        name = "Users",
        description = "All methods to work with users data of the system"
)
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Get all users from the service
     *
     * @return returns list of all modified users by mapper
     */
    @GetMapping("/all")
    @Operation(summary = "Get information about all users")
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
    @Operation(summary = "Get information about user by id")
    public ResponseEntity<UserDTO> getUserById(@PathVariable
                                               @Parameter(description = "user id", example = "1")
                                               int id) throws SQLException {
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
     * @param userDTO user you need to add in the system
     * @return returns user which was added
     */
    @PostMapping("/add")
    @Operation(summary = "Add user to the system")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) throws SQLException {
        User user = userMapper.toUser(userDTO);
        userService.addUser(user);
        log.info("Adding new user : {}", user);
        return ResponseEntity.ok(user);
    }

    /**
     * @param userDTO user you need to edit in the system
     * @return returns user which was edited
     */
    @PatchMapping("/edit")
    @Operation(summary = "Edit existing user of the system")
    public ResponseEntity<User> editUser(@RequestBody UserDTO userDTO) throws SQLException {
        User user = userMapper.toUser(userDTO);
        userService.editUser(user);
        log.info("Updating user : {}", user);
        return ResponseEntity.ok(user);
    }

    /**
     * @param id id of the user that need to delete from the system
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user from the system")
    public void deleteUser(@PathVariable
                           @Parameter(description = "user id", example = "1")
                           int id) throws SQLException {
        userService.removeUser(id);
    }
}
