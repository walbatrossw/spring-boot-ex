package com.doubles.bootdemo8.security;

import com.doubles.bootdemo8.domain.Member;
import com.doubles.bootdemo8.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private Member member;

    public SecurityUser(Member member) {
        super(member.getMemberId(), "{noop}" + member.getMemberPw(), makeGrantedAuthority(member.getRoles()));
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {

        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));

        return list;
    }
}
