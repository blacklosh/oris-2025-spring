package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.FormDto;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping(value = "/1")
    public ModelAndView hello1() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello1");
        modelAndView.addObject("obj", "Good project!");
        return modelAndView;
    }

    @GetMapping("/2")
    public String hello2(Model model) {
        List<String> list = List.of("Hello", "world", "java");
        model.addAttribute("objects", list);
        return "hello2";
    }

    @PostMapping
    public String helloPost(FormDto form) {
        System.out.println(form.getData());
        //return "redirect:/hello/1";
        throw new IllegalArgumentException();
    }

    @GetMapping
    public String testParameters(@RequestParam(value = "id",
                                                required = false,
                                                defaultValue = "none") String id,
                                 Model model) {
        model.addAttribute("obj", "identifier = " + id);
        return "hello1";
    }

    @GetMapping("/path/{id}/info")
    public String testPathVariables(Model model,
                                    @PathVariable("id") String id) {
        model.addAttribute("obj", "identifier = " + id);
        return "hello1";
    }
}
