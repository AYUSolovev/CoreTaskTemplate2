package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        for (int i = 0; i < 4; i++) {
            StringBuilder name = new StringBuilder("Name").append(i);
            userService.saveUser(name.toString(), "LastName" + i, (byte) (20 + i));
            System.out.println("User с именем – " + name.toString() + "добавлен в базу данных");
        }
        List<User> userList = userService.getAllUsers();
        System.out.println(userList.toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
