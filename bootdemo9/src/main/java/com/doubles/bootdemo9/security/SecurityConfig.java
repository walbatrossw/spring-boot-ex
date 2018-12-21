package com.doubles.bootdemo9.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final UsersService usersService;

    @Autowired
    public SecurityConfig(DataSource dataSource, UsersService usersService) {
        this.dataSource = dataSource;
        this.usersService = usersService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config configure() called");

        // 페이지 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/article/list").permitAll()
                .antMatchers("/article/read").permitAll()
                .antMatchers("/article/write").hasRole("USER")
                .antMatchers("/article/modify").hasRole("USER")
                .antMatchers("/article/delete").hasRole("USER");

        // 로그인 페이지
        http.formLogin().loginPage("/member/login");

        // 로그아웃 설정
        http.logout()
                .logoutUrl("/member/logout")    // 로그인 페이지
                .logoutSuccessUrl("/member/login")  // 로그인 성공 이후 페이지
                .invalidateHttpSession(true);   // 세션 무효화

        http.userDetailsService(usersService);

    }

}
