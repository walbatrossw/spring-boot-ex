package com.doubles.bootdemo9.member.persistence;

import com.doubles.bootdemo9.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {



}
