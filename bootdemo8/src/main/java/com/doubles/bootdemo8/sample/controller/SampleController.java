package com.doubles.bootdemo8.sample.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class SampleController {

    @GetMapping("/")
    public String main() {
        log.info("index...");
        return "main";
    }

    @GetMapping("/guest")
    public void guest() {
        log.info("guest...");
    }

    @GetMapping("/manager")
    public void forManager() {
        log.info("manager...");
    }

    @GetMapping("/admin")
    public void forAdmin() {
        log.info("admin...");
    }
}
