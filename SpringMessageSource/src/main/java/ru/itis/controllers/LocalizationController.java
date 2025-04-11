package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class LocalizationController {

    private final MessageSource messageSource;

    @GetMapping("/1")
    public String testMessageSource(Model model,
                                    Locale locale) {
        model.addAttribute("text",
                messageSource.getMessage("TITLE", new Object[]{}, locale));
        return "test1";
    }

    @GetMapping("/2")
    public String mainPage(Model model,
                           Locale locale,
                           @RequestParam(defaultValue = "User") String user) {
        model.addAttribute("title",
                messageSource.getMessage("TITLE", new Object[]{}, locale));
        model.addAttribute("description",
                messageSource.getMessage("DESC", new Object[]{}, locale));
        model.addAttribute("greeting",
                messageSource.getMessage("GREETING", new Object[]{user}, locale));
        return "main";
    }

    @GetMapping("/3")
    public String mainPageAnotherApproach(Model model,
                                          @RequestParam(defaultValue = "User") String user) {
        model.addAttribute("username", user);
        return "main2";
    }

}
