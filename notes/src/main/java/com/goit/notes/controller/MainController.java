package com.goit.notes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String doGet(Model model) {

        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");

        return "index";
    }

    @PostMapping
    public String doPost() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String error, String logout, Model model) {

        model.addAttribute("title", "Login");
        model.addAttribute("message", "Login or create new account");

        if (error != null) log.info("You login or password not corrected, check it and try again");
        if (logout != null) log.info("You have been logged out");
        return "login";
    }

}