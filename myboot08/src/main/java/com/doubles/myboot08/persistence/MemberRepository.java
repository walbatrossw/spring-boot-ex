package com.doubles.myboot08.persistence;

import com.doubles.myboot08.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
