package com.quang.simple.controllers;
import com.quang.simple.services.LoginService;
import com.quang.simple.services.Utils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.util.Optional;

@Controller
public class LoginController {

    private final LoginService myService;
    public LoginController(LoginService myService){
        this.myService = myService;
    }

    @GetMapping("/login")
    public String loginGet(
            @CookieValue("username") Optional<String> usernameCookie
    ) {
        if (Utils.isUserLoggedIn(usernameCookie)) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(
            @CookieValue("username") Optional<String> usernameCookie,
            @RequestParam("username") Optional<String> usernameForm,
            @RequestParam("password") Optional<String> passwordForm,
            HttpServletResponse response,
            Model model,
            @Value("${MaxAge}") int MaxAge
    ) throws Exception {
        if (Utils.isUserLoggedIn(usernameCookie)) {
            return "redirect:/";
        }
        var username = usernameForm.orElse("");
        var password = passwordForm.orElse("");
        if (myService.checkLogin(username,password)) {
            var cookie =
                    ResponseCookie.from("username", username).maxAge(MaxAge).build();
            response.addHeader("Set-Cookie", cookie.toString());
            return "redirect:/";
        }
        model.addAttribute("errorMsg", "Wrong username or password")
                .addAttribute("username", username)
                .addAttribute("password", password);
        return "login";
    }
}
