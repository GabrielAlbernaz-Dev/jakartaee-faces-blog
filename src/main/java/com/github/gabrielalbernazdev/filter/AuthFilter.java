package com.github.gabrielalbernazdev.filter;

import java.io.IOException;

import com.github.gabrielalbernazdev.session.UserSession;
import com.github.gabrielalbernazdev.util.HttpUtil;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Inject
    private UserSession userSession;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        final String path = HttpUtil.getHttpRequestURIPath(req);
        boolean isPrivatePath = path.equals("/") 
            || path.equalsIgnoreCase("/private");

        if(isPrivatePath && userSession.getCurrentUser() == null) {
            res.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }
}
