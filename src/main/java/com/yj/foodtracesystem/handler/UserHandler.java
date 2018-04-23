package com.yj.foodtracesystem.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:27 2018/4/23
 */
@Component
public class UserHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles=null;
        for (GrantedAuthority a : authorities) {
            roles=a.getAuthority();
        }
        if (isAdmin(roles)) {
            url = "/admin/home";
        } else if (isFarmer(roles)) {
            url = "/farmer/home";
        } else if (isCoo(roles)) {
            url = "/cooperator/home";
        } else {
            url = "/accessDenied";
        }

        return url;
    }

    private boolean isAdmin(String roles) {
        if (roles.equals("ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isFarmer(String roles) {
        if (roles.equals("FARMER")) {
            return true;
        }
        return false;
    }

    private boolean isCoo(String roles) {
        if (roles.contains("COOPERATOR")) {
            return true;
        }
        return false;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
