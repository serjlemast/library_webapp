package com.example.liberary.controller.index;

import com.example.liberary.controller.base.AbstractHttpController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/orders", "/orders/*"})
public class IndexOrderController extends AbstractHttpController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orderError", "only login user can see orders");
    }

}
