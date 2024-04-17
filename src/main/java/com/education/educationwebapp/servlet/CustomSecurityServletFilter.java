package com.education.educationwebapp.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CustomSecurityServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String path=request.getRequestURI();

        if(path.contains("jsp")) {
            response.sendRedirect("alma");
            return;
        }

        if(path.endsWith("login")){
            request.getSession().getAttribute("loggedInUser");
        }
        if(path.endsWith("login") || path.contains("css") || path.contains("js")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        final Object loggedInUser=request.getSession().getAttribute("loggedInUser");
        if(loggedInUser==null){

            response.sendRedirect("login");
        }else {

            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
