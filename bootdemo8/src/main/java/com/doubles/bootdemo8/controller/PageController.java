package com.doubles.bootdemo8.controller;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class PageController {

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

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/adminSecret")
    public void forAdminSecret() {
        log.info("admin secret");
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/managerSecret")
    public void forManagerSecret() {
        log.info("manager secret");
    }

}
