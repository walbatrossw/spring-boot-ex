package com.doubles.myboot09.security;

import lombok.extern.java.Log;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

        log.info("======== determineTargetUrl ========");

        Object dest = request.getSession().getAttribute("dest");

        String nextUrl = null;

        if (dest != null) {
            request.getSession().removeAttribute("dest");
            nextUrl = (String) dest;
        } else {
            nextUrl = super.determineTargetUrl(request, response);
        }

        log.info("==========" + nextUrl + "==========");
        return nextUrl;
    }
}
