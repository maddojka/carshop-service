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
import java.sql.Statement;
import java.util.Properties;

import static com.soroko.carshop.constants.Constants.*;

/**
 * @author yuriy.soroko
 */


public class LiquibaseConnection {

    Properties conf = PropertiesLoader.loadProperties();
    String schema = conf.getProperty("liquibaseSchemaName");

    public LiquibaseConnection() throws IOException {
    }


    public void liquibaseConnection() throws LiquibaseException {
        try {
            Connection connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
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
