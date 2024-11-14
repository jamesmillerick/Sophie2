package com.fyp.SpringSophie2.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:/EventDashboard.html"; //redirects the opening page
    }
}
