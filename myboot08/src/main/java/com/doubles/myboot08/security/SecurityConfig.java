package com.doubles.myboot08.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("Security config....");

        // 모든 사용자
        http.authorizeRequests().antMatchers("/guest/**").permitAll();

        // MANAGER 권한을 가진 사용자
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");

        // ADMIN 권한을 가진 사용자
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        // 로그인 페이지 보여주기
        http.formLogin().loginPage("/login");

        // 접근 권한 없음 페이지 처리
        http.exceptionHandling().accessDeniedPage("/accessDenied");

        // 로그아웃 처리 페이지 설정
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
    }

    // 로그인 정보 설정 하기 inMemory
   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build auth global....");
        auth.inMemoryAuthentication().withUser("manager").password("1111").roles("MANAGER");
    }

}
