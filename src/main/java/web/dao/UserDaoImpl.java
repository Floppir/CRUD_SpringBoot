package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(User user) {
        if (!user.getName().isEmpty() && !user.getLastName().isEmpty()) {
            em.persist(user);
        } else {
            throw new RuntimeException("Поля создания пользователя не могут быть пустыми");
        }
    }

    @Override
    public List<User> getAll() {
        String jpql = "SELECT e FROM User e";
        TypedQuery<User> query = em.createQuery(jpql, User.class);

        return query.getResultList();
    }

    @Override
    public void removeById(long id) {
        User user = em.find(User.class, id);
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public void update(User user) {
        if (em.find(User.class, user.getId()) != null) {
            if (!user.getName().isEmpty() && !user.getLastName().isEmpty()) {
                em.merge(user);
            } else {
                throw new RuntimeException("Поля пользователя не могут быть пустыми");
            }
        } else {
            throw new RuntimeException("Пользователя с таким id не существует");
        }
    }
}
