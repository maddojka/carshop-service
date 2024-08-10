package com.soroko.carshop.repository;

import com.soroko.carshop.entity.Car;
import com.soroko.carshop.jdbc.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuriy.soroko
 */
public class CarRepository extends Repository<Car, Integer> {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public CarRepository() throws SQLException {
    }

    public List<Car> getAllCars() throws SQLException {
        String getAllCarsSql = "SELECT * " +
                "FROM carshop.tb_cars ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllCarsSql);
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
    public Integer insert(Car car) throws SQLException {
        String insertDataSql = "INSERT INTO carshop.tb_cars (id, make, model, year, price, condition)" +
                " VALUES(nextval('carshop.cars_sequence'),?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertDataSql);
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
        String updateCarSql =
                "UPDATE carshop.tb_cars SET make = ?, model = ?, year = ?, price = ?, condition = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateCarSql);
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
        String deleteCarSql = "DELETE FROM carshop.tb_cars WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteCarSql);
        preparedStatement.setInt(1, integer);
        preparedStatement.executeUpdate();
    }

    @Override
    public Car findById(Integer integer) throws SQLException {
        String findCarByIdSql = "SELECT * FROM carshop.tb_cars WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findCarByIdSql);
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
        String deleteAllCarsSql = "DELETE FROM carshop.tb_cars";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteAllCarsSql);
        preparedStatement.executeUpdate();
    }
}
