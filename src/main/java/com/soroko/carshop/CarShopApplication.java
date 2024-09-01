package com.soroko.carshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This class launch the application and create the spring context
 * Also this class calls database and liquibase configuration
 * @author yuriy.soroko
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class CarShopApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CarShopApplication.class, args);
    }
}
