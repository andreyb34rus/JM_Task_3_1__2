package ru.andreyb34rus.servicies;

import org.springframework.security.core.userdetails.UserDetails;
import ru.andreyb34rus.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(long id);

    UserDetails getUserByName(String userName);

    void save(User user);

    void delete(User user);

    void update(int id, User user);
}
