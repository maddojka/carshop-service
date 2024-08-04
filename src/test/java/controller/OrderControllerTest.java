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

}
