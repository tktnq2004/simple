package com.quang.simple.controllers;


import com.quang.simple.services.MathService;
import com.quang.simple.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MathController {

    private final MathService myService;

    public MathController(MathService myService) {
        this.myService = myService;
    }

    @GetMapping("/math")
    public String mathGet(
            @CookieValue("username") Optional<String> usernameCookie
    ) {
        if (!Utils.isUserLoggedIn(usernameCookie)) {
            return "redirect:/login";
        }
        return "math";
    }

    @PostMapping("/math")
    public String mathPost(@RequestParam(required = false) Integer input, Model model){
        if (input == null) {
            model.addAttribute("errorMsg", "Input is required");
            return "math";
        }
        if (input < 0 ){
            model.addAttribute("errorMsg","N is must nagative")
                    .addAttribute("input",input);
            return "math";
        }

        if (input < 2){
            model.addAttribute("errorMsg","input must be greater or equal than 2")
            .addAttribute("input",input);
            return  "math";
        }

        List<Integer> primes = myService.listPrime(input);

        model.addAttribute("primes",primes)
                .addAttribute("input",input);

        return "math";
    }
}
