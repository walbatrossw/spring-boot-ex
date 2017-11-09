package com.doubles.myboot09.persistence;

import com.doubles.myboot09.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

}
