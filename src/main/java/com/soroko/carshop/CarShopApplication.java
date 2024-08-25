package com.soroko.carshop;

import com.soroko.carshop.configuration.SpringConfig;
import com.soroko.carshop.liquibase.LiquibaseConnection;
import liquibase.exception.LiquibaseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class CarShopApplication {

    public static void main(String[] args) throws Exception {
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
