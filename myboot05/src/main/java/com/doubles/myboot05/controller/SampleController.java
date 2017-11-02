package com.doubles.myboot05.controller;

import com.doubles.myboot05.domain.MemberVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class SampleController {

    // 1. 객체를 화면에 출력하기 1
    @GetMapping("/sample1")
    public void sample1(Model model) {
        model.addAttribute("greeting", "안녕하세요");
    }

    // 2. 객체를 화면에 출력하기 2
    @GetMapping("/sample2")
    public void sample2(Model model) {
        MemberVo vo = new MemberVo(123, "u00", "p00", "홍길동", new Timestamp(System.currentTimeMillis()));
        model.addAttribute("vo", vo);
    }

    // 3. 리스트를 화면에 출력하기
    @GetMapping("/sample3")
    public void sample3(Model model) {
        List<MemberVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MemberVo(123, "u1" + i, "p0" + i, "홍길동" + i, new Timestamp(System.currentTimeMillis())));
        }
        model.addAttribute("list", list);
    }

    // 4. 지역변수의 선언, if ~ unless 제어처리
    @GetMapping("/sample4")
    public void sample4(Model model) {
        List<MemberVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MemberVo(i, "u000" + i %3, "p0000" + i %3, "홍길동" + i, new Timestamp(System.currentTimeMillis())));
        }
        model.addAttribute("list", list);
    }

    // 5. 인라인 스타일로 thymeleaf 사용하기 1
    @GetMapping("/sample5")
    public void sample5(Model model) {
        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    // 6. 인라인 스타일로 thymeleaf 사용하기 2
    @GetMapping("/sample6")
    public void sample6(Model model) {
        List<MemberVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            list.add(new MemberVo(i, "u000" + i %3, "p0000" + i %3, "홍길동" + i, new Timestamp(System.currentTimeMillis())));
        }
        model.addAttribute("list", list);
        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    // 7. 유틸리티 객체
    @GetMapping("/sample7")
    public void sample7(Model model) {
        model.addAttribute("now", new Date());
        model.addAttribute("price", 100000);
        model.addAttribute("title", "this is a just sample.");
        model.addAttribute("options", Arrays.asList("AAA", "BBB", "CCC", "DDD"));
    }

    // 8. 링크 처리
    @GetMapping("/sample8")
    public void sample8(Model model) {

    }

    // 9. 레이아웃 기능
    @GetMapping("/sample9")
    public void sample9(Model model) {

    }

    // 10. 레이아웃 적용하기
    @GetMapping("/sample/hello")
    public void hello() {

    }

    @GetMapping("/layout/layout1")
    public void layout1() {

    }

}
