package com.doubles.bootdemo6.board.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log
public class WebBoardController {

    @GetMapping("/index")
    public void main() {
        log.info("main...");
    }

}
