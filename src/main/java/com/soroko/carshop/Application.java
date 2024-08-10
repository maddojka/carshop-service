package com.soroko.carshop;

import com.soroko.carshop.controller.CarShopManager;
import com.soroko.carshop.liquibase.LiquibaseConnection;
import liquibase.exception.LiquibaseException;

import java.sql.SQLException;

public class Application {

    public static void main(String[] args)  {
        LiquibaseConnection liquibaseConnection = new LiquibaseConnection();
        try {
            liquibaseConnection.liquibaseConnection();
            CarShopManager carShopManager = new CarShopManager();
            carShopManager.startCarShopLoop();
        } catch (LiquibaseException | SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }


    }
}
