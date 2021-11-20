package com.goit.notes.controller;

import com.goit.notes.entity.NoteUser;
import com.goit.notes.exception.ImpossibleActionException;
import com.goit.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("title", "Registration");
        model.addAttribute("message", "Register for using note service");
        return "register";
    }

    @PostMapping("/register")
    public String registrationUser(@Valid NoteUser noteUser, BindingResult result) {
        try {
            if (result.hasErrors()) return "register";
            userService.register(noteUser);
            return "redirect:/login";
        } catch (ImpossibleActionException e) {
            log.error("User with this username already exist");
            return "register";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/listUsers")
    public ModelAndView showAllUsersPage(ModelAndView modelAndView, Model model) {

        model.addAttribute("title", "List of Users");
        model.addAttribute("message", "Hello, admin! This is list of users, who use notes services");

        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("listUsers");

        return modelAndView;
    }

    @GetMapping(path = "/delete_user")
    public String delete(@RequestParam("noteUser") NoteUser noteUser) {
        userService.delete(noteUser.getId());
        return "redirect:/user/listUsers";
    }

    @ModelAttribute("user")
    public NoteUser defaultUser() {
        return new NoteUser();
    }

}
