package com.doubles.bootdemo9;

import com.doubles.bootdemo9.member.domain.Member;
import com.doubles.bootdemo9.member.domain.MemberRole;
import com.doubles.bootdemo9.member.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {

        for (int i = 1; i <= 100; i++) {

            Member member = new Member();
            member.setMemberEmail("user" + i + "@email.com");
            member.setMemberPw("pw" + i);
            member.setMemberName("회원" + i);

            MemberRole role = new MemberRole();

            if (i <= 90) {
                role.setRoleName("BASIC");
            } else {
                role.setRoleName("ADMIN");
            }

            member.setRoles(Arrays.asList(role));
            memberRepository.save(member);
        }

    }

    @Test
    public void testRead() {

        Optional<Member> result = memberRepository.findById("user99@email.com");
        result.ifPresent(member -> log.info("member" + member));

    }

    @Test
    public void updateEncodePw() {
        List<String> memberEmails = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            memberEmails.add("user" + i + "@email.com");
        }
        memberRepository.findAllById(memberEmails).forEach(member -> {
            member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
            memberRepository.save(member);
        });
    }
}
