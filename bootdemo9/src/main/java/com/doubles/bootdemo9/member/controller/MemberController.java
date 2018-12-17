package com.doubles.bootdemo9.member.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {

    // 회원가입 페이지
    @GetMapping("/register")
    public void register() {
        log.info("register() called ...");
    }

    // 로그인 페이지
    @GetMapping("/login")
    public void login() {
        log.info("login() called ...");
    }
}
