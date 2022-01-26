package ru.andreyb34rus.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.andreyb34rus.models.Role;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Role> getAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Role");
        return query.getResultList();
    }

    @Override
    public Role getById(long id) {
        return sessionFactory.getCurrentSession().find(Role.class, id);
    }

    @Override
    public Role getByName(String roleName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Role r where r.roleName = :roleNameParam");
        query.setParameter("roleNameParam", roleName);
        return (Role) query.getSingleResult();
    }

    @Override
    public void save(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public void delete(Role role) {
        sessionFactory.getCurrentSession().delete(role);
    }

    @Override
    public void update(Role role) {
        sessionFactory.getCurrentSession().merge(role);
    }
}
