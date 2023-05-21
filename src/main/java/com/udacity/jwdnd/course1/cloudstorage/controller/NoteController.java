package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping(value = "users/{username}/notes")
@PreAuthorize("#username == authentication.name")
public class NoteController {
    private final NoteService noteService;
    private final LoginService loginService;

    public NoteController(NoteService noteService, LoginService loginService) {
        this.noteService = noteService;
        this.loginService = loginService;
    }

    @PostMapping
    public String saveOne(@PathVariable String username, @ModelAttribute(name = "noteInput") @Valid Note note, BindingResult bindingResult, Model model) {
        String redirectController = "redirect:/home/users/".concat(username).concat("/save-note-fail");
        User foundUser = loginService.findOneByUsername(username);
        if (noteService.isValidNote(note, bindingResult)) {
            note.setUserId(foundUser.getId());
            Integer savedId = noteService.saveOne(note);
            if (Objects.nonNull(savedId)) {
                redirectController = "redirect:/home/users/".concat(username).concat("/save-note-successfully");
            }
        }
        return redirectController;
    }

    @PostMapping(value = "update")
    public String updateOne(@PathVariable String username, @ModelAttribute(name = "noteInput") @Valid Note note, BindingResult bindingResult, Model model) {
        String redirectController = "redirect:/home/users/".concat(username).concat("/save-note-fail");
        User foundUser = loginService.findOneByUsername(username);
        if (noteService.isValidNote(note, bindingResult)) {
            note.setUserId(foundUser.getId());
            Integer savedId = noteService.updateOne(note);
            if (Objects.nonNull(savedId)) {
                redirectController = "redirect:/home/users/".concat(username).concat("/save-note-successfully");
            }
        }
        return redirectController;
    }

    @GetMapping(value = "{noteId}/delete")
    public String deleteOne(@PathVariable String username, @PathVariable Integer noteId) {
        String redirectController = "redirect:/home/users/".concat(username).concat("/delete-note-fail");
        User foundUser = loginService.findOneByUsername(username);
        int numberNoteDeleted = noteService.deleteOneByUserIdAndId(foundUser.getId(), noteId);
        if (numberNoteDeleted > 0) {
            redirectController = "redirect:/home/users/".concat(username).concat("/delete-note-successfully");
        }
        return redirectController;
    }

}
