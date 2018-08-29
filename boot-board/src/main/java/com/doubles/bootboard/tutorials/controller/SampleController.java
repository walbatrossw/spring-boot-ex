package com.doubles.bootboard.tutorials.controller;

import com.doubles.bootboard.tutorials.domain.SampleVO;
import com.doubles.bootboard.tutorials.domain.SampleVO2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }

    @GetMapping("/samplevo")
    public SampleVO makeSample() {

        SampleVO sampleVO = new SampleVO();
        sampleVO.setVal1("v1");
        sampleVO.setVal2("v2");
        sampleVO.setVal3("v3");

        return sampleVO;
    }

    @GetMapping("/samplevo2")
    public SampleVO2 makeSample2() {

        SampleVO2 sampleVO2 = new SampleVO2();
        sampleVO2.setVal1("v1");
        sampleVO2.setVal2("v2");

        return sampleVO2;
    }
}
