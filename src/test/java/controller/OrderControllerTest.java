package controller;

import com.soroko.carshop.controller.OrderController;
import com.soroko.carshop.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 */
public class OrderControllerTest {

    @Test
    public void Order_service_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            OrderController.orderServiceIsNull(null);
        });
    }

    @Test
    public void Order_service_isNotNull() {
        OrderController.orderServiceIsNull(new OrderService());
    }

    /*@Test
    public void getOrders_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.getOrders(null);
    }

    @Test
    public void getOrdersByCarModel_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.getOrderByCarModel(null);
    }

    @Test
    public void getOrdersByUser_OrderService_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.getOrderByUser(null, new UserService());
    }

    @Test
    public void getOrdersByUser_UserService_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.getOrderByUser(new OrderService(), null);
    }

    @Test
    public void getOrderByDate_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.getOrderByDate(null);
    }

    @Test
    public void createOrder_OrderService_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.createOrder(null,
                new UserService(), new CarService());
    }

    @Test
    public void createOrder_UserService_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.createOrder(new OrderService(),
                null, new CarService());
    }

    @Test
    public void createOrder_CarService_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.createOrder(new OrderService(),
                new UserService(), null);
    }

    @Test
    public void editOrder_OrderService_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.editOrder(null,
                new UserService(), new CarService());
    }

    @Test
    public void editOrder_UserService_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.editOrder(new OrderService(),
                null, new CarService());
    }

    @Test
    public void cancelOrder_isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.cancelOrder(null);
    }

    @Test
    public void completeOrder__isNull() {
        OrderAction orderAction = new OrderAction();
        orderAction.completeOrder(null);
    }*/
}
