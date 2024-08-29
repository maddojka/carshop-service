package com.soroko.carshop.constants;

/**
 * All constants should be written in this class
 * @author yuriy.soroko
 * @version 1.0
 */
public class Constants {
    public static final String GET_ALL_CARS_SQL = "SELECT * FROM carshop.tb_cars ";
    public static final String INSERT_CAR_SQL = "INSERT INTO carshop.tb_cars (id, make, model, year, price, condition) " +
            "VALUES(nextval('carshop.cars_sequence'),?,?,?,?,?)";
    public static final String UPDATE_CAR_SQL =
            "UPDATE carshop.tb_cars SET make = ?, model = ?, year = ?, price = ?, condition = ? WHERE id = ? ";
    public static final String DELETE_CAR_SQL = "DELETE FROM carshop.tb_cars WHERE id = ?";
    public static final String FIND_CAR_BY_ID_SQL = "SELECT * FROM carshop.tb_cars WHERE id = ?";
    public static final String DELETE_ALL_CARS_SQL = "DELETE FROM carshop.tb_cars";
    public static final String GET_ALL_ORDERS_SQL = "SELECT * " + "FROM carshop.tb_orders ";
    public static final String INSERT_ORDER_SQL = "INSERT INTO carshop.tb_orders (id, user_id, car_id, status, created_at) " +
            "VALUES(nextval('carshop.orders_sequence'),?,?,?,?)";
    public static final String UPDATE_ORDER_SQL =
            "UPDATE carshop.tb_orders SET user_id = ?, car_id = ?, status = ?, created_at = ?  WHERE id = ? ";
    public static final String DELETE_ORDER_SQL = "DELETE FROM carshop.tb_orders WHERE id = ?";
    public static final String FIND_ORDER_BY_ID_SQL = "SELECT * FROM carshop.tb_orders WHERE id = ?";
    public static final String DELETE_ALL_ORDERS_SQL = "DELETE FROM carshop.tb_orders";
    public static final String GET_ALL_USERS_SQL = "SELECT * FROM carshop.tb_users ";
    public static final String INSERT_USER_SQL =
            "INSERT INTO carshop.tb_users (id, username, password, email, number_of_purchases, role) " +
                    "VALUES(nextval('carshop.users_sequence'),?,?,?,?,?)";
    public static final String UPDATE_USER_SQL =
            "UPDATE carshop.tb_users SET username = ?, password = ?, email = ?, role = ? WHERE id = ? ";
    public static final String DELETE_USER_SQL = "DELETE FROM carshop.tb_users WHERE id = ?";
    public static final String FIND_USER_BY_ID_SQL = "SELECT * FROM carshop.tb_users WHERE id = ?";
    public static final String DELETE_ALL_USERS_SQL = "DELETE FROM carshop.tb_users";
}
