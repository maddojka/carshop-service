package com.soroko.carshop.repository;

import com.soroko.carshop.entity.Car;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.soroko.carshop.constants.Constants.*;
import org.springframework.stereotype.Repository;

/**
 * This class consists SQL queries to get, receive, add or remove car
 * from database
 *
 * @author yuriy.soroko
 */
//@Repository
@org.springframework.stereotype.Repository
public class CarRepository extends Repository<Car, Integer> {


    private Connection connection;

    public CarRepository(DataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }

    public CarRepository() {
    }

    public List<Car> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_CARS_SQL);
        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
            Car car = new Car();
            car.setId(resultSet.getInt("id"));
            car.setMake(resultSet.getString("make"));
            car.setModel(resultSet.getString("model"));
            car.setYear(resultSet.getInt("year"));
            car.setPrice(resultSet.getInt("price"));
            car.setCondition(resultSet.getString("condition"));
            cars.add(car);
        }
        return cars;
    }

    @Override
    public Integer save(Car car) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR_SQL);
        preparedStatement.setString(1, car.getMake());
        preparedStatement.setString(2, car.getModel());
        preparedStatement.setInt(3, car.getYear());
        preparedStatement.setDouble(4, car.getPrice());
        preparedStatement.setString(5, car.getCondition());
        preparedStatement.executeUpdate();
        return car.getId();
    }

    @Override
    public void update(Car car) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR_SQL);
        preparedStatement.setString(1, car.getMake());
        preparedStatement.setString(2, car.getModel());
        preparedStatement.setInt(3, car.getYear());
        preparedStatement.setDouble(4, car.getPrice());
        preparedStatement.setString(5, car.getCondition());
        preparedStatement.setInt(6, car.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void deleteById(Integer integer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR_SQL);
        preparedStatement.setInt(1, integer);
        preparedStatement.executeUpdate();
    }

    @Override
    public Car getById(Integer integer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_CAR_BY_ID_SQL);
        preparedStatement.setInt(1, integer);
        ResultSet resultSet = preparedStatement.executeQuery();
        Car car = new Car();
        while (resultSet.next()) {
            car.setId(resultSet.getInt("id"));
            car.setMake(resultSet.getString("make"));
            car.setModel(resultSet.getString("model"));
            car.setYear(resultSet.getInt("year"));
            car.setPrice(resultSet.getInt("price"));
            car.setCondition(resultSet.getString("condition"));
        }
        return car;
    }

    public void deleteAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_CARS_SQL);
        preparedStatement.executeUpdate();
    }
}