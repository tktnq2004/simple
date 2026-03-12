package com.quang.simple.services;

import com.quang.simple.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public boolean checkLogin(String username,String password){
        if(loginRepository.checkUser(username,password)){
            return true;
        }
        return false;
    }
}
