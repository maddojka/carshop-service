package com.soroko.carshop;

import com.soroko.carshop.configuration.SpringConfig;
import com.soroko.carshop.liquibase.LiquibaseConnection;
import liquibase.exception.LiquibaseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * This class launch the application and create the spring context
 * Also this class calls database and liquibase configuration
 * @author yuriy.soroko
 */
public class CarShopApplication {

    public static void main(String[] args) throws Exception {
        // Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        try {
            LiquibaseConnection liquibaseConnection = new LiquibaseConnection();
            liquibaseConnection.liquibaseConnection();
        } catch (LiquibaseException e) {
            System.out.println("Liquibase exception: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
