package com.quang.simple.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MathService {
    public List<Integer> listPrime (Integer input) {

        List<Integer> primes = new ArrayList<>();

        for (var i = 2; i <= input; i++)
            if (Utils.checkPrime(i))
                primes.add(i);

        return primes;

    }
}
