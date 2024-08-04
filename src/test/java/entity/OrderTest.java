package entity;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 */
public class OrderTest {

    @Test
    public void Order_null_User() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(null, new Car(), Order.Status.IN_PROGRESS);
        });

    }

    @Test
    public void Order_null_Car() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(new User(), null, Order.Status.IN_PROGRESS);
        });
    }

    @Test
    public void Order_null_Status() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(new User(), new Car(), null);
        });
    }

    @Test
    public void Order_is_Ok() {
        Order order = new Order(new User(), new Car(), Order.Status.IN_PROGRESS);
        order.setStatus(Order.Status.COMPLETED);
        order.getStatus();
        order.setOrderId(2);
        order.getOrderId();
        order.setUser(new User());
        order.getUser();
        order.setCar(new Car());
        order.getCar();
        order.toString();
    }
}
