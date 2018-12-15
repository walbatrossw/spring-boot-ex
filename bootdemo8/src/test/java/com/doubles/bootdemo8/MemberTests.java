package com.doubles.bootdemo8;

import com.doubles.bootdemo8.sample.domain.Member;
import com.doubles.bootdemo8.sample.domain.MemberRole;
import com.doubles.bootdemo8.sample.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInsert() {
        for (int i = 0; i <= 100; i++) {
            Member member = new Member();
            member.setMemberId("user" + i);
            member.setMemberPw("pw" + i);
            member.setMemberName("사용자" + i);

            MemberRole role = new MemberRole();
            if (i <= 80) {
                role.setRoleName("BASIC");
            } else if (i <= 90) {
                role.setRoleName("MANAGER");
            } else {
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));
            memberRepository.save(member);
        }
    }

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.findById("user85");
        result.ifPresent(member -> log.info("member : " + member));
    }
}
