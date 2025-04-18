package ru.itis.springbootexample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.springbootexample.repository.MessageRepository;

@Controller
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final MessageRepository repository;

    @GetMapping("/1")
    public String hello1() {
        return "hello_page";
    }

    @GetMapping("/2")
    public String messagesList(Model model) {
        model.addAttribute("messages", repository.findAll());
        return "messages";
    }

}
