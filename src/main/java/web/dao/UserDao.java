package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    List<User> getAll();

    void removeById (long id);

    void update (User user);

}
