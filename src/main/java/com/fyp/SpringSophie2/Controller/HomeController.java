package com.fyp.SpringSophie2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
Home controller was suggested by ChatGPT. Prompt: I need a way to have the Login page as the opening page when I run the application
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "login"; //redirects the opening page
    }
}
