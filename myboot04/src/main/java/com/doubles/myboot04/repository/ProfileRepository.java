package com.doubles.myboot04.repository;

import com.doubles.myboot04.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
