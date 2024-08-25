package com.soroko.carshop.repository;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.jdbc.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.soroko.carshop.constants.Constants.*;

/**
 * @author yuriy.soroko
 */
@org.springframework.stereotype.Repository
public class UserRepository extends Repository<User, Integer> {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public UserRepository() throws SQLException, IOException {
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL);
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(User.Role.valueOf(resultSet.getString("role")));
            users.add(user);
        }
        return users;
    }

    @Override
    public Integer insert(User user) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(INSERT_USER_SQL);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getNumberOfPurchases());
        preparedStatement.setString(5, user.getRole().toString());
        preparedStatement.executeUpdate();
        return user.getId();
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getRole().toString());
        preparedStatement.setInt(5, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteById(Integer integer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
        preparedStatement.setInt(1, integer);
        preparedStatement.executeUpdate();
    }

    @Override
    public User findById(Integer integer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL);
        preparedStatement.setInt(1, integer);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(User.Role.valueOf(resultSet.getString("role")));
        }
        return user;
    }

    public void deleteAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_USERS_SQL);
        preparedStatement.executeUpdate();
    }
}
