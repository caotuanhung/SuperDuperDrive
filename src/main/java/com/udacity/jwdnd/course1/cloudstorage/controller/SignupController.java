package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "signup")
public class SignupController {

    private SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping
    public String showSignupPage(@ModelAttribute("signupInput") User user) {
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute("signupInput") @Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        String pageName = "signup";
        if (!bindingResult.hasErrors()) {
            Map<Object, Object> resultDetails = signupService.saveOne(user);
            if ((Integer) resultDetails.get("id") > 0) {
                redirectAttributes.addFlashAttribute("resultDetails", resultDetails);
                pageName = "redirect:/login";
            } else {
                model.addAttribute("resultDetails", resultDetails);
            }
        }
        return pageName;
    }
}
