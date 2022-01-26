package ru.andreyb34rus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.andreyb34rus.models.User;
import ru.andreyb34rus.servicies.RoleService;
import ru.andreyb34rus.servicies.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userServiceImpl; //использовать только интерфейс UserService! Иначе приложение падает.
    private final RoleService roleServiceImpl;

    @Autowired
    public AdminController(UserService userServiceImpl, RoleService roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/list")
    public String showUsers(Model model) {
        model.addAttribute("users", userServiceImpl.getAll());
        return "/list";
    }

    // вызов страницы для нового юзера и передача ей пустого объекта User
    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("emptyUser", new User());
        model.addAttribute("roleList", roleServiceImpl.getAll());
        return "/newUser";
    }

    // получаем объект user из тела post запроса пользователя по аттрибуту "user"
    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("user") User user) {
        userServiceImpl.save(user);
        return "redirect:/admin/list";
    }

    // удаление пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImpl.delete(userServiceImpl.getById(id));
        return "redirect:/admin/list";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.getById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userServiceImpl.update((int) user.getId(), user);
        return "redirect:/admin/list";
    }
}
