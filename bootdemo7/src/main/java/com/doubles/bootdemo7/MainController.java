package com.doubles.bootdemo7;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class MainController {

    @GetMapping("/main")
    public void main() {
        log.info("main");
    }

}
