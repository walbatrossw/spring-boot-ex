package com.doubles.bootboard.tutorials.member;

import com.doubles.bootboard.tutorials.member.domain.Member;
import com.doubles.bootboard.tutorials.member.domain.Profile;
import com.doubles.bootboard.tutorials.member.persistence.MemberRepository;
import com.doubles.bootboard.tutorials.member.persistence.ProfileRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ProfileRepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileRepository profileRepository;

    // 회원 입력 테스트
    @Test
    public void testInsertMembers() {
        IntStream.range(1, 101).forEach(i -> {
            Member member = new Member();
            member.setMemberId("user" + i);
            member.setMemberPw("pw" + i);
            member.setMemberName("사용자" + i);
            memberRepository.save(member);
        });
    }

    // 특정 회원의 프로파일 데이터 처리 테스트
    @Test
    public void testInsertProfile() {
        Member member = new Member();
        member.setMemberId("user1");
        for (int i = 1; i < 5; i++) {
            Profile profile = new Profile();
            profile.setFileName("face" + i + ".jpg");
            if (i == 1) {
                profile.setCurrent(true);
            }
            profile.setMember(member);
            profileRepository.save(profile);
        }
    }

    // 특정 회원의 프로필 사진 갯수 조회
    @Test
    public void testFetchJoin() {
        List<Object[]> result = memberRepository.getMemberWithProfileCount("user1");
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    // 특정 회원의 정보, 프로필 사진 정보 조회
    @Test
    public void testFetchJoin2() {
        List<Object[]> result = memberRepository.getMemberWithProfile("user1");
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
}
