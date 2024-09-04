package com.soroko.carshop.controller;

import com.soroko.auditstarter.annotations.EnableLoggable;
import com.soroko.carshop.dto.OrderDTO;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.mapper.OrderMapper;
import com.soroko.carshop.service.OrderService;
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
import java.time.LocalDate;
import java.util.List;

/**
 * This class consists REST API logic of orders
 *
 * @author yuriy.soroko
 * @version 1.0
 */
@Slf4j
@EnableLoggable
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(
        name = "Orders",
        description = "All methods to work with orders data of the system"
)
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    /**
     * Get all orders from the service
     *
     * @return returns list of all modified orders by mapper
     */
    @GetMapping("/all")
    @Operation(summary = "Get information about all orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() throws SQLException {
        List<Order> orders = orderService.getOrders();
        List<OrderDTO> orderDTOS = orderMapper.toOrderDTOList(orders);
        return ResponseEntity.ok(orderDTOS);
    }

    /**
     * Get order by id
     *
     * @param id id of the order
     * @return returns order data
     */
    @GetMapping("/get")
    @Operation(summary = "Get information about order by id")
    public ResponseEntity<OrderDTO> getOrderById(
                                                 @Parameter(description = "order id", example = "1")
                                                 @RequestParam int id
                                                 ) throws SQLException {
        var order = orderService.getOrder(id);
        if (order == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);
        return ResponseEntity.ok(orderDTO);
    }

    /**
     * Add order to the system
     *
     * @param orderDTO order you need to add in the system
     * @return returns order which was added
     */
    @PostMapping("/add")
    @Operation(summary = "Add order to the system")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDTO) throws SQLException {
        var order = orderMapper.toOrder(orderDTO);
        orderService.addOrder(order);
        log.info("Adding new order: {}", order);
        return ResponseEntity.ok(order);
    }

    /**
     * @param orderDTO order you need to edit in the system
     * @return returns order which was edited
     */
    @PatchMapping("/edit")
    @Operation(summary = "Edit existing order of the system")
    public ResponseEntity<Order> editOrder(@RequestBody OrderDTO orderDTO) throws SQLException {
        var order = orderMapper.toOrder(orderDTO);
        orderService.editOrder(order);
        log.info("Updating order: {}", order);
        return ResponseEntity.ok(order);
    }

    /**
     * @param id id of the order that need to delete from the system
     */
    @DeleteMapping("/delete")
    @Operation(summary = "Delete order from the system")
    public void deleteOrder(
                            @Parameter(description = "order id", example = "1")
                            @RequestParam int id) throws SQLException {
        orderService.cancelOrder(id);
    }

    /**
     * @param id id of the order that need to complete in the system
     */
    @PostMapping("/complete")
    @Operation(summary = "Complete order of the system")
    public void completeOrder(@RequestParam int id) throws SQLException {
        orderService.completeOrder(id);
    }

    /**
     * @param date date to filter
     * @return returns list of all filtered orders by condition
     */
    @GetMapping("/getbydate")
    @Operation(summary = "Get order from the system by date")
    public ResponseEntity<List<OrderDTO>> getOrderByDate(@RequestParam
                                                         @Parameter(description = "date", example = "2024-01-01")
                                                         String date) throws SQLException {
        LocalDate localDate = date == null ? null : LocalDate.parse(date);
        List<Order> orders = orderService.findBy(date);
        if (orders == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<OrderDTO> ordersDTO = orderMapper.toOrderDTOList(orders);
        return ResponseEntity.ok(ordersDTO);
    }

    /**
     * @param status status to filter
     * @return returns list of all filtered orders by make
     */
    @GetMapping("/getbystatus")
    @Operation(summary = "Get order from the system by status")
    public ResponseEntity<List<OrderDTO>> getOrderByStatus(@RequestParam
                                                           @Parameter(description = "status", example = "created")
                                                           String status) throws SQLException {
        List<Order> orders = orderService.findBy(status);
        if (orders == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<OrderDTO> ordersDTO = orderMapper.toOrderDTOList(orders);
        return ResponseEntity.ok(ordersDTO);
    }

    /**
     * @param id id of the user to filter
     * @return returns list of all filtered orders by make
     */
    @GetMapping("/getbyuser")
    @Operation(summary = "Get order from the system by user id")
    public ResponseEntity<List<OrderDTO>> getOrderByUser(@RequestParam
                                                         @Parameter(description = "user id", example = "1")
                                                         int id) throws SQLException {
        User user = userService.getUser(id);
        List<Order> orders = orderService.findBy(user);
        if (orders == null) {
            ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<OrderDTO> ordersDTO = orderMapper.toOrderDTOList(orders);
        return ResponseEntity.ok(ordersDTO);
    }
}
