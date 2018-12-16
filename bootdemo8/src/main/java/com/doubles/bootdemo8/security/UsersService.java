package com.doubles.bootdemo8.security;

import com.doubles.bootdemo8.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Log
@Service
public class UsersService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public UsersService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //User sampleUser = new User(username, "{noop}1111", Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")));
        //memberRepository.findById(username).ifPresent(member -> log.info("" + member));

        return memberRepository.findById(username)
                .filter(member -> member != null)
                .map(member -> new SecurityUser(member)).get();
    }
}
