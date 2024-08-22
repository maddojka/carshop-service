package com.soroko.carshop.controller;

import com.soroko.carshop.servlet.CarServlet;
import com.soroko.carshop.servlet.OrderServlet;
import com.soroko.carshop.servlet.UserServlet;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarShopManager {
    private final CarServlet carServlet;
    private final UserServlet userServlet;
    private final OrderServlet orderServlet;

    public CarShopManager() throws SQLException, IOException {
        carServlet = new CarServlet();
        userServlet = new UserServlet();
        orderServlet = new OrderServlet();
    }
}


