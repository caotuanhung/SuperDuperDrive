package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "login")
public class LoginController {

    private final LoginService loginService;
    private final HashService hashService;

    public LoginController(LoginService loginService, HashService hashService) {
        this.loginService = loginService;
        this.hashService = hashService;
    }

    @GetMapping
    public String showLoginPage(@ModelAttribute("loginInput") User user) {
        return "login";
    }

//    @PostMapping
//    public String showHomePage(@ModelAttribute("loginInput") User user) {
//        return "redirect:/home/users/".concat(user.getUsername());
//    }
}
