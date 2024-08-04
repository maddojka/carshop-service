package controller;

import com.soroko.carshop.controller.Menu;
import com.soroko.carshop.service.CarService;
import com.soroko.carshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 */
public class MenuTest {

    private Menu menu = new Menu();

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    public void menu_RegisterOperation_UserService_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
               menu.registerOperation(null));
    }

    @Test
    public void menu_LoginOperation_UserService_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                menu.loginOperation(null));
    }

    @Test
    public void menu_printCarsMenu_CarService_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                menu.printCarsMenu(null));
    }

    @Test
    public void menu_printUsersMenu_UserService_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                menu.printUsersMenu(null));
    }

    @Test
    public void menu_printOrdersMenu_OrderService_isNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                menu.printOrdersMenu(null, new UserService(), new CarService()));
    }



    @Test
    public void testMenu() {
        Menu menu = new Menu();
        menu.isAdmin();
        menu.isClient();
        menu.isManager();
        menu.setClient(true);
        menu.setManager(true);
        menu.setAdmin(true);
    }
}
