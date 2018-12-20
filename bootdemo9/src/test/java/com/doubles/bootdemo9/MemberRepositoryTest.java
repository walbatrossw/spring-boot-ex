package com.doubles.bootdemo9;

import com.doubles.bootdemo9.member.domain.Member;
import com.doubles.bootdemo9.member.domain.MemberRole;
import com.doubles.bootdemo9.member.persistence.MemberRepository;
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
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {

        for (int i = 0; i <= 100; i++) {
            Member member = new Member();
            member.setEmail("user0" + (i%10) + "@mail.com");
            member.setPassword("pw" + i);
            member.setName("회원" + i);

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

        Optional<Member> result = memberRepository.findById("user99@mail.com");
        result.ifPresent(member -> log.info("member" + member));

    }

}
