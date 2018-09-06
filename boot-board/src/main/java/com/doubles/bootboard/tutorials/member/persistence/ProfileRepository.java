package com.doubles.bootboard.tutorials.member.persistence;

import com.doubles.bootboard.tutorials.member.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
