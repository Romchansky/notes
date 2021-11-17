package com.goit.notes.controller;

import com.goit.notes.entity.User;
import com.goit.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("title", "Registration");
        model.addAttribute("message","Register for using note service");
        return "register";
    }


    @PostMapping("/register")
    public String registrationUser(@Valid User user, BindingResult result)
    {
        if(result.hasErrors ()){
            return "register";
        }

        userService.register(user);
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/listUsers")
    public ModelAndView showAllUsersPage(ModelAndView modelAndView, Model model) {
        model.addAttribute("title", "List of Users");
        model.addAttribute("message", "Hello, admin! This is list of users, who use notes services");
        modelAndView.addObject ("users", userService.findAll ());
        modelAndView.setViewName ("listUsers");
        return modelAndView;
    }

    @ModelAttribute("user")
    public User defaultUser() {
        return new User();
    }
}
