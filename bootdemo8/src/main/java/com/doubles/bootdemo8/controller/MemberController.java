package com.doubles.bootdemo8.controller;

import com.doubles.bootdemo8.domain.Member;
import com.doubles.bootdemo8.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/join")
    public void join() {
        log.info("join get ... ");
    }

    @Transactional
    @PostMapping("/join")
    public String join(@ModelAttribute("member") Member member) {
        log.info("join post ... ");
        log.info("member : " + member);

        String encryptPw = passwordEncoder.encode(member.getMemberPw());

        log.info("password encode : " + encryptPw);

        member.setMemberPw(encryptPw);
        memberRepository.save(member);

        return "/member/joinResult";
    }
}
