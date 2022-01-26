package ru.andreyb34rus.servicies;

import ru.andreyb34rus.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role getById(long id);

    Role getByName(String roleName);

    void save(Role role);

    void delete(Role role);

    void update(Role role);
}
