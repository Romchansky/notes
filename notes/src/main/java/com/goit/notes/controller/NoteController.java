package com.goit.notes.controller;


import com.goit.notes.entity.Note;
import com.goit.notes.entity.User;
import com.goit.notes.service.NoteService;
import com.goit.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    @GetMapping("/listNotes")
    public ModelAndView listAllNotes(@AuthenticationPrincipal User user, ModelAndView modelAndView, Model model) {
        Optional<User> currentUser = userService.findByName(user.getUserName());


        model.addAttribute("title", "List Notes");
        model.addAttribute("message", "list all notes");

        modelAndView.addObject("noteList", currentUser.get().getNotes());
        modelAndView.setViewName("listNote");

        return modelAndView;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Create Note");
        model.addAttribute("message", "Create Note");
        return "create";
    }

    @PostMapping("/create")
    public String createNote(@Valid Note note) {
        noteService.create(note);
        return "redirect:/notes";
    }

    @GetMapping("/edit")
    public String edit(UUID id, Model model) {
        model.addAttribute("message", "Edit note");
        Optional<Note> byId = noteService.findById(id);
        model.addAttribute("id", byId.get().getId());
        model.addAttribute("name", byId.get().getName());
        model.addAttribute("description", byId.get().getDescription());
        model.addAttribute("access", byId.get().getAccess());
        return "edit";
    }

    @PostMapping("/edit")
    public String editNote(@Valid Note note) {
        return "redirect:/notes";
    }

    @GetMapping("/share/{id}")
    public String shareNote(@PathVariable UUID id, Note note) {

        Optional<Note> noteById = noteService.findById(id);

        return "share";
    }

}
