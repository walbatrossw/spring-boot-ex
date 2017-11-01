package com.doubles.myboot04.repository;

import com.doubles.myboot04.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    // 1. JPA join 처리 : 회원정보, 프로필 사진의 숫자
    @Query("SELECT m.uid, count(p) FROM Member m LEFT OUTER JOIN Profile p ON m.uid = p.member WHERE m.uid = ?1 GROUP BY m")
    public List<Object[]> getMemberWithProfileCount(String uid);

    // 2. JPA join 처리 : 회원정보, 프로필 사진의 숫자
    @Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p ON m.uid = p.member WHERE m.uid = ?1 AND p.current = true ")
    public List<Object[]> getMemberWithProfile(String uid);
}
