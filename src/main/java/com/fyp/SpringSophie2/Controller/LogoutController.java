package com.fyp.SpringSophie2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate the session or clear the authentication context
        request.getSession().invalidate();
        return "redirect:/login"; // Redirect to the login page
    }
}
