package com.goit.notes.controller;

import com.goit.notes.entity.Note;
import com.goit.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/note")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/create")
    public RedirectView createNote(Note note) {
        //   todo ;
    }

    @PostMapping("/edit")
    public RedirectView updateNote(Note note) {
        //todo;
    }

    @GetMapping("/list")
    public ModelAndView findAllNotes() {
        //todo
    }

    @GetMapping("/delete")
    public RedirectView deleteNote() {
        //todo
    }

    @GetMapping("/share/id")
    public RedirectView share() {

        //todo
    }
}
