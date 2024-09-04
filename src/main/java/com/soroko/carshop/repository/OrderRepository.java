package com.soroko.carshop.repository;

import com.soroko.carshop.constants.Constants;
import com.soroko.carshop.entity.Car;
import com.soroko.carshop.entity.Order;
import com.soroko.carshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.soroko.carshop.constants.Constants.*;
import org.springframework.stereotype.Repository;

/**
 * This class consists SQL queries to get, receive, add or remove order
 * from database
 *
 * @author yuriy.soroko
 */
@Repository
public class OrderRepository extends Repository<Order, Integer> {

    private final CarRepository carRepository;

    private final UserRepository userRepository;
    private Connection connection;

    public OrderRepository(CarRepository carRepository, UserRepository userRepository, DataSource dataSource) throws SQLException {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        connection = dataSource.getConnection();
    }

    public List<Order> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERS_SQL);
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            int carId = resultSet.getInt("car_id");
            int userId = resultSet.getInt("user_id");
            order.setCar(carRepository.getById(carId));
            order.setUser(userRepository.getById(userId));
            order.setStatus(Order.Status.valueOf(resultSet.getString("status")));
            order.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Integer save(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Constants.INSERT_ORDER_SQL);
        preparedStatement.setInt(1, order.getUser().getId());
        preparedStatement.setInt(2, order.getCar().getId());
        preparedStatement.setString(3, order.getStatus().toString());
        preparedStatement.setDate(4, Date.valueOf(order.getCreatedAt()));
        preparedStatement.executeUpdate();
        return order.getId();
    }

    @Override
    public void update(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_SQL);
        preparedStatement.setInt(1, order.getUser().getId());
        preparedStatement.setInt(2, order.getCar().getId());
        preparedStatement.setString(3, order.getStatus().toString());
        preparedStatement.setDate(4, Date.valueOf(order.getCreatedAt()));
        preparedStatement.setInt(5, order.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteById(Integer integer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_SQL);
        preparedStatement.setInt(1, integer);
        preparedStatement.executeUpdate();
    }

    @Override
    public Order getById(Integer integer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID_SQL);
        preparedStatement.setInt(1, integer);
        ResultSet resultSet = preparedStatement.executeQuery(/*FIND_ORDER_BY_ID_SQL*/);
        Order order = new Order();
        if (resultSet.next()) {
            order.setId(resultSet.getInt("id"));
            order.setCar((Car) resultSet.getObject("car_id"));
            order.setUser((User) resultSet.getObject("user_id"));
            order.setStatus(Order.Status.valueOf(resultSet.getString("status")));
            order.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
        }
        return order;
    }

    public void deleteAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_ORDERS_SQL);
        preparedStatement.executeUpdate();
    }
}