package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping(value = "users/{username}/credentials")
@PreAuthorize("#username == authentication.name")
public class CredentialController {
    private final LoginService loginService;
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService, LoginService loginService) {
        this.credentialService = credentialService;
        this.loginService = loginService;
    }

    @PostMapping
    public String saveOne(@PathVariable String username, @ModelAttribute(name = "credentialInput") @Valid Credential credential, BindingResult bindingResult, Model model) {
        String redirectController = "redirect:/home/users/".concat(username).concat("/save-credential-fail");
        User foundUser = loginService.findOneByUsername(username);
        if (credentialService.isValidCredentialInput(credential, bindingResult)) {
            credential.setUserId(foundUser.getId());
            Integer savedId = credentialService.saveOne(credential);
            if (Objects.nonNull(savedId)) {
                redirectController = "redirect:/home/users/".concat(username).concat("/save-credential-successfully");
            }
        }
        return redirectController;
    }

    @GetMapping(value = "{id}")
    @ResponseBody
    public Credential getById(@PathVariable String username, @PathVariable Integer id) {
        User foundUser = loginService.findOneByUsername(username);
        return credentialService.findByUserIdAndId(foundUser.getId(), id);
    }

    @PostMapping(value = "update")
    public String updateOne(@PathVariable String username, @ModelAttribute(name = "credentialInput") @Valid Credential credential, BindingResult bindingResult, Model model) {
        String redirectController = "redirect:/home/users/".concat(username).concat("/save-credential-fail");
        User foundUser = loginService.findOneByUsername(username);
        if (credentialService.isValidCredentialInput(credential, bindingResult)) {
            credential.setUserId(foundUser.getId());
            Integer savedId = credentialService.updateOne(credential);
            if (Objects.nonNull(savedId)) {
                redirectController = "redirect:/home/users/".concat(username).concat("/save-credential-successfully");
            }
        }
        return redirectController;
    }

    @GetMapping(value = "{credentialId}/delete")
    public String deleteOne(@PathVariable String username, @PathVariable Integer credentialId) {
        String redirectController = "redirect:/home/users/".concat(username).concat("/delete-credential-fail");
        User foundUser = loginService.findOneByUsername(username);
        Integer numberCredentialDeleted = credentialService.deleteOneByUserIdAndId(foundUser.getId(), credentialId);
        if (Objects.nonNull(numberCredentialDeleted) && numberCredentialDeleted > 0) {
            redirectController = "redirect:/home/users/".concat(username).concat("/delete-credential-successfully");
        }
        return redirectController;
    }

}
