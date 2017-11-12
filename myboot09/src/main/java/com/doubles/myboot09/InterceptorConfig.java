package com.doubles.myboot09;


import com.doubles.myboot09.interceptor.LoginCheckInterceptor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Log
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
