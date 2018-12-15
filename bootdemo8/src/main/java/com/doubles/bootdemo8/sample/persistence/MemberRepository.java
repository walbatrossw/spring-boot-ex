package com.doubles.bootdemo8.sample.persistence;

import com.doubles.bootdemo8.sample.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

}
