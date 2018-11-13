package com.doubles.bootdemo4.persistence;

import com.doubles.bootdemo4.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    // Fetch Join

    // 회원정보 + 회원 프로필 사진 개수
    @Query("SELECT m.mid, count(p) FROM Member m LEFT OUTER JOIN Profile p ON m.mid = p.member WHERE m.mid = ?1 GROUP BY m")
    public List<Object[]> getMemberWithProfileCount(String mid);

    // 회원정보 + 사용중인 프로필 사진
    @Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p ON m.mid = p.member WHERE m.mid = ?1 AND p.current = true")
    public List<Object[]> getMemberWithProfile(String mid);
}
