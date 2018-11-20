package com.doubles.bootdemo6;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class MainController {

    @GetMapping("/main")
    public void main() {
        log.info("main() called ...");
    }

}
