package com.example.liberary.filter;

import com.example.liberary.model.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String servletPath = request.getServletPath();
        String method = request.getMethod();

        HttpSession session = request.getSession();

        // Logout
        if (servletPath.equals("logout.jsp") || servletPath.contains("/logout")) {
            request.getRequestDispatcher("/logout").forward(request, response);
            return;
        }

        // access user for special page
        if (session != null && session.getAttribute("sessionUserRole") != null) {
            String path = requestURI.substring(contextPath.length());

            Role role = Role.getRole(session.getAttribute("sessionUserRole"));
            String url = Role.getUrl(role);

            if (path.startsWith(url)) {
                request.getRequestDispatcher(path).forward(request, response);
            }

            return;
        }

        // Login
        if (servletPath.equals("login.jsp") || servletPath.contains("/login")) {
            request.getRequestDispatcher("/login").forward(request, response);
            return;
        }

        // home
        if (servletPath.contains("index.jsp")) {
            request.getRequestDispatcher("").forward(request, response);
            return;
        }


        //todo orders
        if (servletPath.contains("/books") || servletPath.contains("/orders")) {
            String path = requestURI.substring(contextPath.length());
            request.getRequestDispatcher(path).include(request, response);
            return;
        }


        // allow css style
        if (request.getRequestURI().endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }

        // allow js scripts
        if (request.getRequestURI().endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        // allow icon images
        if (request.getRequestURI().endsWith(".ico")) {
            chain.doFilter(request, response);
            return;
        }
    }
}
