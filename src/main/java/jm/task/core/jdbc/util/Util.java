package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Util instance;
    private static SessionFactory sessionFactory;
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/productdb?serverTimezone=Europe/Moscow&useSSL=false",
                    "root",
                    "106519");
            Properties properties = new Properties();
            properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost/productdb?serverTimezone=Europe/Moscow&useSSL=false");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "106519");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            Configuration cfg = new Configuration()
                    .setProperties(properties);
            cfg.addAnnotatedClass(User.class);
            sessionFactory = cfg.buildSessionFactory();
        } catch (Exception sqlE) {
            sqlE.printStackTrace();
        }
    }

    private Util() {
    }

    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null) {
                    instance = new Util();
                }
            }
        }
        return instance;
    }

    public Connection getConnection () {
        return connection;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
