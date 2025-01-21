/*
package com.fyp.SpringSophie2.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

//This file handles role-based redirection upon Login, i.e., when the event manager
//signs in, they will be redirected to the dashboard and when a normal staff member signs in
//they will be redirected to a page displaying their assigned tasks
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

    protected String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_EVENT_MANAGER") || role.equals("ROLE_WEDDING_COORDINATOR") ||
            role.equals("ROLE_GENERAL_MANAGER") || role.equals("ROLE_HOTEL_MANAGER")) {
                return "/manager/dashboard";
            } else {
                return "/tasks/assigned";
            }
        }
        throw new IllegalStateException("No valid role found");
    }
}

 */

