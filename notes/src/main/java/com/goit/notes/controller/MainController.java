package com.goit.notes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

    @GetMapping
    public String doGet() {
        return "redirect:/note/list";
    }
    

    @GetMapping("login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "You login or password not corrected, check it " +
                    "and try again");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out");
        }
        return "login";
    }

}
