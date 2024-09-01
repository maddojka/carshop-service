package com.soroko.carshop.configuration;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class consists liquibase connection
 *
 * @author yuriy.soroko
 */
@Configuration
public class LiquibaseConfiguration {

    /**
     * Liquibase properties
     */
    @Value("${liquibaseSchemaName}")
    private String schema;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public LiquibaseConfiguration() {
    }


    /**
     * Constructor which creates connection with database
     * @param url url of the database
     * @param user user of the database
     * @param password password of the database
     */
    public void liquibaseConnection(String url, String user, String password) {
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

    /**
     * Method which creates new liquibase connection
     * @return liquibase configuration object
     */
    @Bean
    public LiquibaseConfiguration getConnection() {
        LiquibaseConfiguration liquibaseConfiguration = new LiquibaseConfiguration();
        liquibaseConfiguration.liquibaseConnection(url, user, password);
        return liquibaseConfiguration;
    }

}