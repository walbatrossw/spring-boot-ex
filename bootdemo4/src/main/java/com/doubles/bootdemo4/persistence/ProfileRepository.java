package com.doubles.bootdemo4.persistence;

import com.doubles.bootdemo4.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
