package ru.itis.springsecurity1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/sign-in")
    public String signInPage() {
        return "signIn";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "signUp";
    }

}
