package com.soroko.carshop.service;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author yuriy.soroko
 */
public class DataSourceTest {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    public DataSourceTest() {
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?currentSchema=service");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
    }
}
