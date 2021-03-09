package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/productdb?serverTimezone=Europe/Moscow&useSSL=false",
                    "root",
                    "106519");
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }


    public static Connection getConnection() {
        return connection;
    }
}
