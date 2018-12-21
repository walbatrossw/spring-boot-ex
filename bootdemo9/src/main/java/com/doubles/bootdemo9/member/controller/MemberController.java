package com.doubles.bootdemo9.member.controller;

import com.doubles.bootdemo9.member.domain.Member;
import com.doubles.bootdemo9.member.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public void register() {
        log.info("register() called ...");
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@ModelAttribute("member")Member member, RedirectAttributes redirectAttributes) {

        log.info("MEMBER : " + member);
        String encryptPw = passwordEncoder.encode(member.getMemberPw());

        log.info("encoded password : " + encryptPw);
        member.setMemberPw(encryptPw);

        memberRepository.save(member);
        redirectAttributes.addFlashAttribute("msg", "register success");

        return "redirect:/member/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public void login() {
        log.info("login() called ...");
    }
}
