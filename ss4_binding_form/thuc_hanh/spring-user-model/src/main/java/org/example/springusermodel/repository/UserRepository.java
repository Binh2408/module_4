package org.example.springusermodel.repository;

import org.example.springusermodel.model.Login;
import org.example.springusermodel.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final List<User> users;
    static {
        users = new ArrayList<>();
        users.add(new User("binh", "123456", "John", "john@codegym.vn", 18));
        users.add(new User("bill", "123456", "Bill", "bill@codegym.vn", 20));
        users.add(new User("mike", "123456", "Mike", "mike@codegym.vn", 22));
    }

    public static User checkLogin(Login login) {
        for (User user : users) {
            if (user.getAccount().equals(login.getAccount())
                    && user.getPassword().equals(login.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
