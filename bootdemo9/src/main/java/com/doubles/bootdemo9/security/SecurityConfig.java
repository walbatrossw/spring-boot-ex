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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config configure() called");

        http.authorizeRequests()
                .antMatchers("/article/list").permitAll()
                .antMatchers("/article/read").permitAll()
                .antMatchers("/article/write").hasRole("USER")
                .antMatchers("/article/modify").hasRole("USER")
                .antMatchers("/article/delete").hasRole("USER");

        http.formLogin().loginPage("/member/login");
    }
}
