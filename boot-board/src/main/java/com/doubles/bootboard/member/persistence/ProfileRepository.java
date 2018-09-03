package com.doubles.bootboard.member.persistence;

import com.doubles.bootboard.member.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
