package com.goit.notes.controller;

import com.goit.notes.entity.Note;
import com.goit.notes.entity.User;
import com.goit.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/listNotes")
    public ModelAndView listAllNotes(User user, ModelAndView modelAndView, Model model) {
        model.addAttribute("title", "List Notes");
        model.addAttribute("message", "list all notes");
        modelAndView.addObject("note", user.getNotes());
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
        noteService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/edit")
    public String edit(UUID id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("message", "Edit note");        
        model.addAttribute("id", note.getId());
        model.addAttribute("name", note.getName());
        model.addAttribute("description", note.getDescription());
        model.addAttribute("access", note.getAccess());
        return "edit";
    }

//    @PostMapping("/edit")
//    public String editNote(@Valid Note note) {
//        return "redirect:/notes";
//    }
//
//    @GetMapping("/share/{id}")
//    public String shareNote(@PathVariable UUID id, Note note) {
//        return "share";
//    }
}
