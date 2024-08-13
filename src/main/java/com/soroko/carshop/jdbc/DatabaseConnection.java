package com.soroko.carshop.jdbc;


import com.soroko.carshop.configuration.PropertiesLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static com.soroko.carshop.constants.Constants.*;

/**
 *
 * @author Yuriy Soroko
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    Properties conf = PropertiesLoader.loadProperties();
    String url = conf.getProperty("url");
    String user = conf.getProperty("db_username");
    String password = conf.getProperty("db_password");



    private DatabaseConnection() throws SQLException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute(String.format("CREATE SCHEMA IF NOT EXISTS %s", "service"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}