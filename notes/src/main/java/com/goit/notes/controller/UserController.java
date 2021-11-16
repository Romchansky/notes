package com.goit.notes.controller;

import com.goit.notes.entity.User;
import com.goit.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

public class UserController {
    private static final Log log = LogFactory.getLog (UserController.class);

    private final UserService userService;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("title", "Registration");
        return "register";
    }

    @PostMapping("/register")
    public String registrationUser(@Valid User user, BindingResult result, Model model)
    {
        if(result.hasErrors ()){
            return "register";
        }

        userService.register(user);
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listUsers")
    public ModelAndView showAllUsersPage(ModelAndView model) {
        model.addObject("users", userService.findAll());
        model.setViewName("listUsers");
        return model;
    }

    @ModelAttribute("user")
    public User defaultUser() {
        return new User();
    }
}

