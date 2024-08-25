package com.soroko.carshop.liquibase;

import com.soroko.carshop.configuration.PropertiesLoader;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author yuriy.soroko
 */


public class LiquibaseConnection {

    private Properties conf = PropertiesLoader.loadProperties();
    private String schema = conf.getProperty("liquibaseSchemaName");
    private String url = conf.getProperty("url");
    private String user = conf.getProperty("db_username");
    private String password = conf.getProperty("db_password");

    public LiquibaseConnection() throws IOException {
    }


    public void liquibaseConnection() throws LiquibaseException {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Database database =
                    DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName(schema);
            Liquibase liquibase = new Liquibase("db/changelog/changelog.xml",
                    new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Migration is completed successfully");
        } catch (SQLException | LiquibaseException e) {
            System.out.println("SQL Exception in migration " + e.getMessage());
        }
    }
}