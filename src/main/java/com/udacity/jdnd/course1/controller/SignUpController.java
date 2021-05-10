package com.udacity.jdnd.course1.controller;

import com.udacity.jdnd.course1.consts.ErrorMessages;
import com.udacity.jdnd.course1.forms.SignUpForm;
import com.udacity.jdnd.course1.model.User;
import com.udacity.jdnd.course1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.udacity.jdnd.course1.consts.ErrorMessages.*;

@Controller
public class SignUpController {
    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute("signupForm") SignUpForm signupForm, Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute("signupForm") SignUpForm signupForm, Model model) {
        model.addAttribute("signupForm", new SignUpForm());
        User user = new User();
        user.setPassword(signupForm.getPassword());
        user.setUsername(signupForm.getUsername());
        user.setFirstName(signupForm.getFirstName());
        user.setLastName(signupForm.getLastName());
        if (!userService.isUsernameAvailable(signupForm.getUsername())) {
            model.addAttribute("signupError", String.format(USERNAME_ALREADY_EXIST, signupForm.getUsername()));
            return "signup";
        }
        model.addAttribute("signupSuccess", true);
        userService.createUser(user);
        return "signup";
    }
}
