package com.doubles.bootdemo8.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UsersService usersService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config...");

        // 권한에 따른 접근 가능 페이지 설정
        http.authorizeRequests().antMatchers("/guest/**").permitAll();
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        // 권한에 따른 접근 불가 페이지 설정
        http.exceptionHandling().accessDeniedPage("/accessDenied");

        // 로그인 페이지
        http.formLogin().loginPage("/login");

        // 로그아웃 처리
        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true);

        // 로그인 유지
        http.rememberMe()
                .key("doubles")
                .userDetailsService(usersService)
                .tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60*60*24);
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build Auth global ...");
        auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());
    }

    private PersistentTokenRepository getJDBCRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("build Auth global ... ");
//
//        String query1 = "SELECT member_id username, CONCAT('{noop}',member_pw) password, true enabled FROM tb_members WHERE member_id = ?";
//        String query2 = "SELECT member member_id, role_name role FROM tb_member_roles WHERE member = ?";
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(query1)
//                .rolePrefix("ROLE_")
//                .authoritiesByUsernameQuery(query2);
//    }

//    // 메모리
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("build Auth global ...");
//
//        auth.inMemoryAuthentication()
//                .withUser("manager")
//                .password("{noop}1111")
//                .roles("MANAGER");
//    }

}
