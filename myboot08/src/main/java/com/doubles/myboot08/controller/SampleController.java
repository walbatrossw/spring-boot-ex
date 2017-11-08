package com.doubles.myboot08.controller;

import lombok.extern.java.Log;
import org.springframework.security.access.annotation.Secured;
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

    // mvc 애너테이션 처리
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/adminSecret")
    public void forAdminSecret() {
        log.info("admin secret");
    }

    // mvc 애너테이션 처리
    @Secured({"ROLE_MANAGER"})
    @GetMapping("/managerSecret")
    public void forManagerSecret() {
        log.info("manager secret");
    }
}
