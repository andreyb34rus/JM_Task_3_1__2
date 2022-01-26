package ru.andreyb34rus.servicies;

import org.springframework.stereotype.Service;
import ru.andreyb34rus.dao.RoleDAO;
import ru.andreyb34rus.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAll() {
        return roleDAO.getAll();
    }

    @Override
    public Role getById(long id) {
        return roleDAO.getById(id);
    }

    @Override
    public Role getByName(String roleName) {
        return roleDAO.getByName(roleName);
    }

    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDAO.delete(role);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }
}
