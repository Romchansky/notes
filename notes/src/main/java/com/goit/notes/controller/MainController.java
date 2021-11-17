package com.goit.notes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String doGet(Model model) {

        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");

        return "index";
    }

    @PostMapping
    public String doPost() {
        return "index";
    }

    @GetMapping("/login")
    public String login(String error, String logout, Model model) {

        model.addAttribute("title", "Login");
        model.addAttribute("message", "To start using login or create an account");

        if (error != null) log.info("You login or password not corrected, check it and try again");
        if (logout != null) log.info("You have been logged out");

        return "login";
    }

}
