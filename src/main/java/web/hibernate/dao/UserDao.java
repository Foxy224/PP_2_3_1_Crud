package web.hibernate.dao;

import web.hibernate.entity.User;


import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();

    User getById(long id);

    void update(long id, User user);

    void delete(long id);
}
