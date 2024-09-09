package com.anifrozenfoods.anifrozenfoods.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logoutPage")
    public String logoutPage() {
        return "logout"; // Returns the logout.html template
    }
}
