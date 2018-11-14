package com.doubles.bootdemo5.controller;

import com.doubles.bootdemo5.domain.MemberVO;
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

    // 문자열 출력
    @GetMapping("/sample1")
    public void sample1(Model model) {
        //model.addAttribute("greeting", "hello world");
        model.addAttribute("greeting", "안녕하세요");
    }

    // 객체 출력
    @GetMapping("/sample2")
    public void sample2(Model model) {
        MemberVO memberVO = new MemberVO(123, "u00", "p00", "더블에스", new Timestamp(System.currentTimeMillis()));
        model.addAttribute("memberVO", memberVO);
    }

    // 리스트 출력
    @GetMapping("/sample3")
    public void sample3(Model model) {
        List<MemberVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MemberVO(123, "u0" + i, "p0" + i, "더블에스" + i, new Timestamp(System.currentTimeMillis())));
        }
        model.addAttribute("list", list);
    }

    // 지역변수 선언 if ~ unless 제어처리
    @GetMapping("/sample4")
    public void sample4(Model model) {
        List<MemberVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MemberVO(i, "u000" + i%3, "p0" + i%3, "더블에스" + i, new Timestamp(System.currentTimeMillis())));
        }
        model.addAttribute("list", list);
    }

    // 인라인 스타일로 thymeleaf 사용 1
    @GetMapping("/sample5")
    public void sample5(Model model) {
        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    // 인라인 스타일로 thymeleaf 사용 2
    @GetMapping("/sample6")
    public void sample6(Model model) {
        List<MemberVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MemberVO(i, "u000" + i%3, "p0" + i%3, "더블에스" + i, new Timestamp(System.currentTimeMillis())));
        }
        model.addAttribute("list", list);
        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    // thymeleaf 유틸리티 객체 사용
    @GetMapping("/sample7")
    public void sample7(Model model) {
        model.addAttribute("now", new Date());
        model.addAttribute("price", 123456789);
        model.addAttribute("title", "this is a just sample");
        model.addAttribute("options", Arrays.asList("AAA", "BBB", "CCC", "DDD"));
    }

    // thymeleaf 링크 처리
    @GetMapping("/sample8")
    public void sample8(Model model) {

    }

    // 페이지 레이아웃 적용
    @GetMapping("/sample/hello")
    public void hello() {

    }

}
