package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    @Override
    public void removeById(long id) {
        try {
            userDao.removeById(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Пользователь с данным id не найден");
        }
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

}
