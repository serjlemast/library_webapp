package com.example.liberary.controller.admin;

import com.example.liberary.controller.base.AbstractHttpController;
import com.example.liberary.exeption.ApplicationException;
import com.example.liberary.model.Book;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@WebServlet(urlPatterns = {"/admin/books", "/admin/books/*"})
public class BookRestController extends AbstractHttpController {

    private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    /**
     * Get all roles from data base
     * Http response statuses:
     * 200 - successful
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Book> books = bookService.findAll();
            String json = toJson(books);
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error getting book " + ex.getMessage());
            buildErrorResponse(404, ex.getMessage(), resp);
        }
    }
}
