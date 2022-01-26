package ru.andreyb34rus.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andreyb34rus.dao.UserDAO;
import ru.andreyb34rus.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails getUserByName(String userName){
        return userDAO.getUserByName(userName);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        userDAO.update(id, user);
    }
}
