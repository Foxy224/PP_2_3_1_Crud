package web.hibernate.dao;

import web.hibernate.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query= (TypedQuery<User>) entityManager.createQuery("from User");
        entityManager.close();
        return query.getResultList();

    }

    @Override
    public User getById(long id) {

        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);

        User user = null;
        for (User u: query.getResultList()) {
            user = u;
        }

        return user;

    }

    @Override
    public void update(long id, User user) {
        User userToEdit = getById(id);

        userToEdit.setFirstName(user.getFirstName());
        userToEdit.setLastName(user.getLastName());
        userToEdit.setEmail(user.getEmail());

        entityManager.merge(userToEdit);
    }

    @Override
    public void delete(long id) {
        User user;
        user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.flush();
        entityManager.clear();
    }


}

