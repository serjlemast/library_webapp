package com.example.liberary.controller;

import com.example.liberary.controller.base.AbstractHttpController;
import com.example.liberary.exeption.ApplicationException;
import com.example.liberary.model.Role;
import com.example.liberary.model.UserCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends AbstractHttpController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String password = req.getParameter("password");
            String username = req.getParameter("username");
            if (password == null || username == null || username.isEmpty() || password.isEmpty()) {
                req.setAttribute("error", "username or password are empty");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            UserCredential userCredential = userService.find(username, password);
            if (userCredential != null) {

                HttpSession session = req.getSession();
                session.setAttribute("sessionUserName", userCredential.getUsername());
                session.setAttribute("sessionUserRole", userCredential.getRole().name());
                session.setAttribute("sessionUserId", userCredential.getId());

                //todo !!!
                String url = Role.getUrl(userCredential.getRole());
                resp.sendRedirect(req.getContextPath() + url);

            } else {
                req.setAttribute("error", "user not found by username: " + username);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (ApplicationException ex) {
            logger.warn("Login error " + ex.getMessage());
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
