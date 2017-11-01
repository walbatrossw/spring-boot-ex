package com.doubles.myboot04;

import com.doubles.myboot04.domain.Member;
import com.doubles.myboot04.domain.Profile;
import com.doubles.myboot04.repository.MemberRepository;
import com.doubles.myboot04.repository.ProfileRepository;
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
@Log    // lombok의 로그를 사용할때 사용
@Commit // 테스트 결과 commit
public class ProfileTests {

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    ProfileRepository profileRepo;


    // 1. 더미 데이터 생성 : 사용자 입력 처리
    @Test
    public void testInsertMember() {
        IntStream.range(1, 101).forEach(i -> {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("사용자" + i);
            memberRepo.save(member);
        });
    }

    // 2. 특정 회원의 프로필 데이터 처리
    @Test
    public void testInsertProfile() {
        Member member = new Member();
        member.setUid("user1");
        for (int i = 1; i < 5; i++) {
            Profile profile1 = new Profile();
            profile1.setFname("face" + i + ".jpg");
            if (i == 1) {
                profile1.setCurrent(true);
            }
            profile1.setMember(member);
            profileRepo.save(profile1);
        }
    }

    // 3. JPA join 처리 : 회원정보, 프로필 사진의 숫자 가져오기
    @Test
    public void testFetchJoin1() {
        List<Object[]> result = memberRepo.getMemberWithProfileCount("user1");
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    // 4. JPA join 처리 : 회원정보, 프로필 정보 가져오기
    @Test
    public void testFetchJoin2() {
        List<Object[]> result = memberRepo.getMemberWithProfile("user1");
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
}
