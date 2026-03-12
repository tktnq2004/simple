package com.quang.simple.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Math.sqrt;

public class Utils {
    public static boolean isUserLoggedIn(Optional<String> usernameCookie) {
        return usernameCookie.isPresent() && usernameCookie.get().equals("admin");
    }
    public static boolean checkPrime(int N){

        for (var i = 2 ; i <= sqrt(N) ; i++)
            if ( N % i == 0)
                return false;
        return true;
    }
}

