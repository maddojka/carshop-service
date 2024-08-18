package com.soroko.carshop;

import com.soroko.carshop.controller.CarShopManager;
import com.soroko.carshop.liquibase.LiquibaseConnection;
import liquibase.exception.LiquibaseException;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) {
        try {
            LiquibaseConnection liquibaseConnection = new LiquibaseConnection();
            liquibaseConnection.liquibaseConnection();
            CarShopManager carShopManager = new CarShopManager();
        } catch (LiquibaseException | SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
