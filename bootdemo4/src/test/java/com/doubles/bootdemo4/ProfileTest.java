package com.doubles.bootdemo4;

import com.doubles.bootdemo4.domain.Member;
import com.doubles.bootdemo4.domain.Profile;
import com.doubles.bootdemo4.persistence.MemberRepository;
import com.doubles.bootdemo4.persistence.ProfileRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log    // 로그 사용
@Commit // 테스트 결과 커밋
public class ProfileTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileRepository profileRepository;

    // 입력
    @Test
    public void testInsertMember() {
        IntStream.range(1, 101).forEach(i -> {
            Member member = new Member();
            member.setMid("user" + i);
            member.setMpw("pw" + i);
            member.setMname("사용자" + i);
            memberRepository.save(member);
        });
    }

    // 특정 회원의 프로필 데이터 처리
    @Test
    public void testInsertProfile() {
        Member member = new Member();
        member.setMid("user1");

        for (int i = 1; i < 5; i++) {
            Profile profile1 = new Profile();
            profile1.setFname("face" + i + ".jpg");
            if (i == 1) {
                profile1.setCurrent(true);
            }
            profile1.setMember(member);
            profileRepository.save(profile1);
        }
    }

    // 단방향 Fetch Join1 : 회원정보 + 프로필 사진 개수
    @Test
    public void testFetchJoin1() {
        List<Object[]> result = memberRepository.getMemberWithProfileCount("user1");
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    // 단방향 Fetch Join2 : 회원정보 + 현재 사용중인 프로필 사진
    @Test
    public void testFetchJoin2() {
        List<Object[]> result = memberRepository.getMemberWithProfile("user1");
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }


}
