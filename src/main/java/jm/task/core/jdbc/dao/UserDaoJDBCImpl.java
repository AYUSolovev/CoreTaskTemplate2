package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        StringBuilder query = new StringBuilder("create table if not exists users( ")
                .append("id int(10) not null auto_increment primary key, ")
                .append("name varchar(50) not null, ")
                .append("last_name varchar(50) not null, ")
                 .append("age int(1) not null )");
        try (Statement st = Util.getConnection().createStatement()) {
            st.execute(query.toString());
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = Util.getConnection().createStatement()) {
            st.execute("drop table if exists users");
        } catch (SQLException sqlE) {
            System.out.println("Не удалось удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        StringBuilder strQuery = new StringBuilder("insert into users(name, last_name, age) values( ")
                .append("'").append(name).append("', ")
                .append("'").append(lastName).append("', ")
                .append(age).append(" )");
        try (Statement st = Util.getConnection().createStatement()) {
            st.execute(strQuery.toString());
        } catch (SQLException sqlE) {
            System.out.println("Не удалось добавить user");
        }
    }

    public void removeUserById(long id) {
        StringBuilder query = new StringBuilder("delete from users where id = ")
                .append(id);
        try (Statement st = Util.getConnection().createStatement()) {
            st.execute(query.toString());
        } catch (SQLException sqlE) {
            System.out.println("Не удалось удалить таблицу");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        StringBuilder query = new StringBuilder("select * from users");
        try (Statement st = Util.getConnection().createStatement(); ResultSet rs = st.executeQuery(query.toString())) {
            User user;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException sqlE) {
            System.out.println("Не удалось удалить таблицу");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement st = Util.getConnection().createStatement()) {
            st.execute("truncate table users");
        } catch (SQLException sqlE) {
            System.out.println("Не удалось очистить таблицу");
        }
    }
}
