package com.doubles.myboot08.security;

import com.doubles.myboot08.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Log
public class UsersService implements UserDetailsService {

    @Autowired
    MemberRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        repo.findById(username).ifPresent(member -> log.info("" + member));

        return repo.findById(username).filter(m -> m != null).map(m -> new SecurityUser(m)).get();
    }
}
