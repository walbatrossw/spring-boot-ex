package com.doubles.bootboard.member.persistence;

import com.doubles.bootboard.member.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    // 특정 회원의 아이디로 프로필 사진 숫자 조회
    @Query("SELECT m.memberId, COUNT(p) FROM Member m LEFT OUTER JOIN Profile p ON m.memberId = p.member WHERE m.memberId = ?1 GROUP BY m")
    public List<Object[]> getMemberWithProfileCount(String memberId);

    // 특정 회원의 정보와 사용중인 프로필 사진에 대한 정보 조회
    @Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p ON m.memberId = p.member WHERE m.memberId = ?1 AND p.current = true")
    public List<Object[]> getMemberWithProfile(String memberId);

}
