package com.soroko.carshop.repository;

import com.soroko.carshop.entity.User;
import com.soroko.carshop.jdbc.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuriy.soroko
 */
public class UserRepository extends Repository<User, Integer> {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public UserRepository() throws SQLException {
    }

    public List<User> getAllUsers() throws SQLException {
        String getAllUsersSql = "SELECT * " +
                "FROM carshop.tb_users ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllUsersSql);
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
        String insertDataSql =
                "INSERT INTO carshop.tb_users (id, username, password, email, number_of_purchases, role) " +
                        "VALUES(nextval('carshop.users_sequence'),?,?,?,?,?)";
        PreparedStatement preparedStatement =
                connection.prepareStatement(insertDataSql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getNumberOfpurchases());
        preparedStatement.setString(5, user.getRole().toString());
        preparedStatement.executeUpdate();
        return user.getId();
    }

    @Override
    public void update(User user) throws SQLException {
        String updateUserSql =
                "UPDATE carshop.tb_users SET username = ?, password = ?, email = ?, role = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateUserSql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getRole().toString());
        preparedStatement.setInt(5, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteById(Integer integer) throws SQLException {
        String deleteUserSql = "DELETE FROM carshop.tb_users WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSql);
        preparedStatement.setInt(1, integer);
        preparedStatement.executeUpdate();
    }

    @Override
    public User findById(Integer integer) throws SQLException {
        String findUserByIdSql = "SELECT * FROM carshop.tb_users WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findUserByIdSql);
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
        String deleteAllUsersSql = "DELETE FROM carshop.tb_users";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteAllUsersSql);
        preparedStatement.executeUpdate();
    }
}
