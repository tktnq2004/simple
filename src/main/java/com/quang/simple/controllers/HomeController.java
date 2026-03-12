package com.quang.simple.controllers;

import com.quang.simple.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(
            @CookieValue("username") Optional<String> usernameCookie,
            Model model
    ) {
        var userLoggedIn = Utils.isUserLoggedIn(usernameCookie);
        model.addAttribute("userLoggedIn", userLoggedIn);
        return "home";
    }
}