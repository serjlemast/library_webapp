package com.example.liberary.controller.reader;

import com.example.liberary.controller.base.AbstractHttpController;
import com.example.liberary.exeption.ApplicationException;
import com.example.liberary.model.Book;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = {"/client/books", "/client/books/*"})
public class ReaderBookController extends AbstractHttpController {

    private static final Logger logger = LoggerFactory.getLogger(ReaderBookController.class);

    /**
     * Get all books from data base
     * Http response statuses:
     * 200 - successful
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        try {
            List<Book> books = bookService.findAll();
            if (pathInfo != null && pathInfo.contains("/find")) {
                String name = req.getParameter("book_name");
                String author = req.getParameter("book_author");
                String orderBy = req.getParameter("orderBy");
                // get book id from path
                int bookId = defineUrlPathParameter(req);
                if (Objects.nonNull(name) || Objects.nonNull(author)) {
                    books.clear();
                    books = (bookService.find(name, author, orderBy));
                }
                if (bookId > 0) {
                    books.clear();
                    books.add(bookService.find(bookId));
                }
            }
            String json = toJson(books);
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error getting books" + ex.getMessage());
            buildErrorResponse(404, ex.getMessage(), resp);
        }
    }
}
