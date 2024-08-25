package com.soroko.carshop.controller;

import com.soroko.carshop.annotations.Loggable;
import com.soroko.carshop.dto.OrderDTO;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.mapper.OrderMapper;
import com.soroko.carshop.service.OrderService;
import com.soroko.carshop.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
@Loggable
@RestController
@RequestMapping("/api/order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {
    final OrderService orderService;
    final UserService userService;
    final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    /**
     * Get all orders from the service
     *
     * @return returns list of all modified orders by mapper
     */
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() throws SQLException {
        List<Order> orders = orderService.getOrders();
        if (orders == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        List<OrderDTO> orderDTOS = orderMapper.toOrderDTOList(orders);
        return ResponseEntity.ok(orderDTOS);
    }

    /**
     * Get order by id
     *
     * @param id id of the order
     * @return returns order data
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) throws SQLException {
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
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDTO) throws SQLException {
        var order = orderMapper.toOrder(orderDTO);
        orderService.addOrder(order);
        return ResponseEntity.ok(order);
    }

    /**
     * @param orderDTO order you need to edit in the system
     * @return returns order which was edited
     */
    @PatchMapping("/edit")
    public ResponseEntity<Order> editOrder(@RequestBody OrderDTO orderDTO) throws SQLException {
        var order = orderMapper.toOrder(orderDTO);
        orderService.editOrder(order);
        return ResponseEntity.ok(order);
    }

    /**
     * @param id id of the order that need to delete from the system
     */
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) throws SQLException {
        orderService.cancelOrder(id);
    }

    /**
     * @param id id of the order that need to complete in the system
     */
    @PostMapping("/complete")
    public void completeOrder(@RequestParam int id) throws SQLException {
        orderService.completeOrder(id);
    }

    /**
     * @param strDate date to filter
     * @return returns list of all filtered orders by condition
     */
    @GetMapping("/getbydate")
    public ResponseEntity<List<OrderDTO>> getOrderByDate(@RequestParam String strDate) throws SQLException {
        LocalDate date = strDate == null ? null : LocalDate.parse(strDate);
        List<Order> orders = orderService.findBy(strDate);
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
    public ResponseEntity<List<OrderDTO>> getOrderByStatus(@RequestParam String status) throws SQLException {
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
    public ResponseEntity<List<OrderDTO>> getOrderByStatus(@RequestParam int id) throws SQLException {
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
