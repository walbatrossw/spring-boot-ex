package com.doubles.bootdemo9.security;

import lombok.extern.java.Log;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

        log.info("----------- determine target url -----------");

        Object destination = request.getSession().getAttribute("destination");
        String nextURL = null;

        if (destination != null) {
            request.getSession().removeAttribute("destination");
            nextURL = (String) destination;
        } else {
            nextURL = super.determineTargetUrl(request, response);
        }

        log.info("----------- " + nextURL + " -----------");

        return nextURL;
    }
}
