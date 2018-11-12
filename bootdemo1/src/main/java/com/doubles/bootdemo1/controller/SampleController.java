package com.doubles.bootdemo1.controller;

import com.doubles.bootdemo1.domain.SampleVO;
import com.doubles.bootdemo1.domain.SampleVO2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/sample")
    public SampleVO makeSample() {

        SampleVO vo = new SampleVO();
        vo.setVal1("v1");
        vo.setVal2("v2");
        vo.setVal3("v3");

        System.out.println("sample : " + vo);

        return vo;
    }

    @GetMapping("/sample2")
    public SampleVO2 makeSample2() {

        SampleVO2 vo2 = new SampleVO2();
        vo2.setVal1("v1");
        vo2.setVal2("v2");
        vo2.setVal3("v3");

        System.out.println("sample : " + vo2);

        return vo2;
    }


}
