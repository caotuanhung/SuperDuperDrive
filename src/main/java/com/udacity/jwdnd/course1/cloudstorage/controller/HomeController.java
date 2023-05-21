package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "home")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final LoginService loginService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, LoginService loginService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.loginService = loginService;
    }

    @GetMapping
    public String showHomePage(Authentication authentication) {
        return "redirect:/home/users/".concat(authentication.getName());
    }

    @GetMapping(value = "users/{username}")
    @PreAuthorize("#username == authentication.name")
    public String showHomePage(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectFileTab(model);
        return "home";
    }

    @GetMapping(value = "users/{username}/upload-file-successfully/{message}")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageWithUploadFileSuccessfully(@PathVariable String username, @PathVariable String message, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectFileTab(model);
        model.addAttribute("isFileUploaded", true);
        model.addAttribute("message", message);
        return "home";
    }

    @GetMapping(value = "users/{username}/upload-file-fail/{message}")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageWithUploadFileError(@PathVariable String username, @PathVariable String message, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectFileTab(model);
        model.addAttribute("isFileUploaded", false);
        model.addAttribute("message", message);
        return "home";
    }

    @GetMapping(value = "users/{username}/delete-file-successfully")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterDeleteFileSuccessfully(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectFileTab(model);
        model.addAttribute("isFileDeleted", true);
        model.addAttribute("message", "Delete file successfully!");
        return "home";
    }

    @GetMapping(value = "users/{username}/save-note-successfully")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterSaveNoteSuccessfully(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectNoteTab(model);
        model.addAttribute("isNoteSaved", true);
        model.addAttribute("message", "Note is saved!");
        return "home";
    }

    @GetMapping(value = "users/{username}/save-note-fail")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterSaveNoteFail(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectNoteTab(model);
        model.addAttribute("isNoteSaved", false);
        model.addAttribute("message", "Can not save the note!");
        return "home";
    }

    @GetMapping(value = "users/{username}/delete-note-fail")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterDeleteNoteFail(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectNoteTab(model);
        model.addAttribute("isNoteDeleted", false);
        model.addAttribute("message", "Can not delete the note!");
        return "home";
    }

    @GetMapping(value = "users/{username}/delete-note-successfully")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterDeleteNoteSuccessfully(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectNoteTab(model);
        model.addAttribute("isNoteDeleted", false);
        model.addAttribute("message", "Delete note successfully!");
        return "home";
    }

    @GetMapping(value = "users/{username}/save-credential-successfully")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterSaveCredentialSuccessfully(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectCredentialTab(model);
        model.addAttribute("isCredentialSaved", true);
        model.addAttribute("message", "Credential is saved!");
        return "home";
    }

    @GetMapping(value = "users/{username}/save-credential-fail")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterSaveCredentialFail(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectCredentialTab(model);
        model.addAttribute("isCredentialSaved", false);
        model.addAttribute("message", "Can not save the credential!");
        return "home";
    }

    @GetMapping(value = "users/{username}/delete-credential-fail")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterDeleteCredentialFail(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectCredentialTab(model);
        model.addAttribute("isCredentialDeleted", false);
        model.addAttribute("message", "Can not delete the credential!");
        return "home";
    }

    @GetMapping(value = "users/{username}/delete-credential-successfully")
    @PreAuthorize("#username == authentication.name")
    public String showHomePageAfterDeleteCredentialSuccessfully(@PathVariable String username, @ModelAttribute(name = "noteInput") Note note, @ModelAttribute(name = "noteUpdateInput") Note noteUpdateInput, @ModelAttribute(name = "credentialInput") Credential credential, @ModelAttribute(name = "credentialUpdateInput") Credential credentialUpdateInput, Model model) {
        setCommonDataForUser(model, username);
        selectCredentialTab(model);
        model.addAttribute("isCredentialDeleted", true);
        model.addAttribute("message", "Delete credential successfully!");
        return "home";
    }

    private void setCommonDataForUser(Model model, String username) {
        User foundUser = loginService.findOneByUsername(username);
        model.addAttribute("files", fileService.findAllByUserIdOrderByIdDesc(foundUser.getId()));
        model.addAttribute("notes", noteService.findAllByUserIdOrderByIdDesc(foundUser.getId()));
        model.addAttribute("credentials", credentialService.findAllByUserIdOrderByIdDesc(foundUser.getId()));
        model.addAttribute("username", username);
    }

    private void selectFileTab(Model model) {
        model.addAttribute("isFileTabSelected", true);
        model.addAttribute("isNoteTabSelected", false);
        model.addAttribute("isCredentialTabSelected", false);
    }

    private void selectNoteTab(Model model) {
        model.addAttribute("isFileTabSelected", false);
        model.addAttribute("isNoteTabSelected", true);
        model.addAttribute("isCredentialTabSelected", false);
    }

    private void selectCredentialTab(Model model) {
        model.addAttribute("isFileTabSelected", false);
        model.addAttribute("isNoteTabSelected", false);
        model.addAttribute("isCredentialTabSelected", true);
    }
}
