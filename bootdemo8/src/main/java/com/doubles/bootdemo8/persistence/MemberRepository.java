package com.doubles.bootdemo8.persistence;

import com.doubles.bootdemo8.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

}
