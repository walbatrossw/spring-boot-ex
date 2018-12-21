package com.doubles.bootdemo9.interceptor;

import lombok.extern.java.Log;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("pre handle ...");

        String destination = request.getParameter("destination");

        if (destination != null) {
            request.getSession().setAttribute("destination", destination);
        }

        return super.preHandle(request, response, handler);
    }
}
