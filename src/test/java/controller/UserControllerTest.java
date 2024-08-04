package controller;

import com.soroko.carshop.controller.UserController;
import com.soroko.carshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 */
public class UserControllerTest {

    @Test
    public void User_service_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UserController.userServiceIsNull(null);
        });
    }

    @Test
    public void Order_service_isNotNull() {
        UserController.userServiceIsNull(new UserService());
    }

  /*  @Test
    public void getUsers_isNull() {
        UserAction userAction = new UserAction();
        userAction.getUsers(null);
    }

    @Test
    public void registerUser_isNull() {
        UserAction userAction = new UserAction();
        userAction.registerUser(null);
    }

    @Test
    public void editUser_isNull() {
        UserAction userAction = new UserAction();
        userAction.editUser(null);
    }

    @Test
    public void removeUser_isNull() {
        UserAction userAction = new UserAction();
        userAction.removeUser(null);
    }*/

}
