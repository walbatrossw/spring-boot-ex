package com.doubles.myboot01.controller;

import com.doubles.myboot01.domain.SampleVO;
import com.doubles.myboot01.domain.SampleVO2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }


    @GetMapping("/sample")
    public SampleVO getSamples() {
        SampleVO vo = new SampleVO();
        vo.setValue1("one");
        vo.setValue2("two");
        vo.setValue3("three");
        System.out.println(vo);
        return vo;
    }

    @GetMapping("/sample2")
    public SampleVO2 getSamples2() {
        SampleVO2 vo2 = new SampleVO2();
        vo2.setValue1("하나");
        vo2.setValue2("둘");
        vo2.setValue3("셋");
        System.out.println(vo2);
        return vo2;
    }
}
