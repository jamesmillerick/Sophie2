package com.fyp.SpringSophie2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showEventDashboard() {
        return "EventDashboard"; //redirects the opening page
    }
}
