package com.goit.notes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

    @GetMapping
    public String doGet() {
        return "index";
    }

    @PostMapping
    public String doPost() {
        return "index";
    }

    @GetMapping("login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
           log.info ("You login or password not corrected, check it " +
                        "and try again");
        }
        if (logout != null) {
                log.info ("You have been logged out");
        }
            return "login";
        }
    }