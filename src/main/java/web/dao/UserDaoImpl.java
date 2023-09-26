package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
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
        em.merge(user);
    }
}
