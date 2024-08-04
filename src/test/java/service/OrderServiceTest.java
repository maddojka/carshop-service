package service;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import com.soroko.carshop.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void addOrder_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.addOrder(null));
    }

    @Test
    public void addOrder_isNotNull() {
        Order order = new Order();
        orderService.addOrder(order);
    }

    @Test
    public void getOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.getOrder(-1));
    }

    @Test
    public void getOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.getOrder(Integer.MAX_VALUE));
    }

    @Test
    public void getOrder_correctId() {
        Order order = new Order();
        orderService.getOrders().add(order);
        orderService.getOrder(0);
    }

    @Test
    public void editOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.editOrder(-1, new Order()));
    }

    @Test
    public void editOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.editOrder(Integer.MAX_VALUE, new Order()));
    }

    @Test
    public void editOrder_correctId() {
        Car car = new Car();
        User user = new User();
        Order order = new Order(user, car, Order.Status.CREATED);
        orderService.getOrders().add(order);
        orderService.editOrder(0, order);
    }

    @Test
    public void cancelOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.cancelOrder(-1));
    }

    @Test
    public void cancelOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.cancelOrder(Integer.MAX_VALUE));
    }

    @Test
    public void cancelOrder_correctId() {
        Car car = new Car();
        User user = new User();
        Order order = new Order(user, car, Order.Status.CREATED);
        orderService.getOrders().add(order);
        orderService.cancelOrder(0);
    }

    @Test
    public void completeOrder_NegativeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.completeOrder(-1));
    }

    @Test
    public void completeOrder_OversizeId() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.completeOrder(Integer.MAX_VALUE));
    }

    @Test
    public void completeOrder_correctId() {
        Order order = new Order();
        orderService.getOrders().add(order);
        orderService.completeOrder(0);
    }

    @Test
    public void findByCarName_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findByCarModel(""));

    }

    @Test
    public void findByCarName_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findByCarModel(null));

    }

    @Test
    public void findByCarName_isOk() {
        orderService.findByCarModel("Lada");
    }

    @Test
    public void findByUser_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy((User) null));

    }

    @Test
    public void findByUser_isOk() {
        orderService.findBy(new User());
    }

    @Test
    public void findByStatus_isEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy(""));

    }

    @Test
    public void findByStatus_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy((String) null));

    }

    @Test
    public void findByStatus_isOk() {
        orderService.findBy("CREATED");
    }

    @Test
    public void findByDate_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                orderService.findBy((LocalDate) null));

    }

    @Test
    public void findByDate_isOk() {
        orderService.findBy(LocalDate.now());
    }


}
