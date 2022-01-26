package ru.andreyb34rus.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.andreyb34rus.models.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u");
        return query.getResultList();
    }

    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    public User getById(long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.id = :idParam");
        query.setParameter("idParam", id);
        return (User) query.getSingleResult();
    }

    public UserDetails getUserByName(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.firstName = :userNameParam");
        query.setParameter("userNameParam", userName);
        return (User) query.getSingleResult();
    }

    public void update(int id, User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("update User u set firstName = :firstNameParam," +
                "lastName = :lastNameParam, age = :ageParam, email = :emailParam where u.id = :idParam");
        query.setParameter("idParam", user.getId());
        query.setParameter("firstNameParam", user.getFirstName());
        query.setParameter("lastNameParam", user.getLastName());
        query.setParameter("ageParam", user.getAge());
        query.setParameter("emailParam", user.getEmail());
        query.executeUpdate();
    }
}
