package ru.andreyb34rus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.andreyb34rus.servicies.UserService;

import java.security.Principal;


@Controller
public class UsersController {

    @Autowired
    private UserService userServiceImpl; //использовать только интерфейс UserService! Иначе приложение падает.

    @GetMapping("/user")
    public String userProfile(Model model, Principal principal){
        model.addAttribute("user", userServiceImpl.getUserByName(principal.getName()));
        return "/user";
    }
}
