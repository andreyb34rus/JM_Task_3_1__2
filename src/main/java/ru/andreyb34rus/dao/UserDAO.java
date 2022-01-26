package ru.andreyb34rus.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.andreyb34rus.models.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    void save(User user);

    void delete(User user);

    User getById(long id);

    UserDetails getUserByName(String userName);

    void update(int id, User user);
}
