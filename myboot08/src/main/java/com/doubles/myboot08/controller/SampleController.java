package com.doubles.myboot08.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class SampleController {

    @GetMapping("/")
    public String index() {
        log.info("index");
        return "index";
    }

    @GetMapping("/guest")
    public void forGuest() {
        log.info("guest");
    }

    @GetMapping("/manager")
    public void forManager() {
        log.info("manager");
    }

    @GetMapping("/admin")
    public void forAdmin() {
        log.info("admin");
    }
}
