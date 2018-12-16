package com.doubles.bootdemo8.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class LoginController {

    @GetMapping("/login")
    public void login() {
        log.info("login page...");
    }

    @GetMapping("/accessDenied")
    public void accessDenied() {
        log.info("accessDenied page...");
    }

    @GetMapping("/logout")
    public void logout() {
        log.info("logout page...");
    }
}
