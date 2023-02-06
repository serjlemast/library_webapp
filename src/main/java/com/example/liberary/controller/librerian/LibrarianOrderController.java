package com.example.liberary.controller.librerian;

import com.example.liberary.controller.base.AbstractHttpController;
import com.example.liberary.exeption.ApplicationException;
import com.example.liberary.model.OrderCredential;
import com.example.liberary.model.Status;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = {"/librarian/orders", "/librarian/orders/*"})
public class LibrarianOrderController extends AbstractHttpController {

    private static final Logger logger = LoggerFactory.getLogger(LibrarianOrderController.class);

    /**
     * Get all orders from data base
     * Http response statuses:
     * 200 - successful
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        try {
            List<OrderCredential> users = orderService.findAll();
            if (pathInfo != null && pathInfo.contains("/find")) {
                String orderStatus = req.getParameter("status");
                String userId = req.getParameter("userId");
                if (Objects.nonNull(userId)) {
                    users.clear();
                    users = orderService.findAll(Status.getStatus(orderStatus),Integer.parseInt(userId));
                }
            }
            String json = toJson(users);
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error getting orders " + ex.getMessage());
            buildErrorResponse(404, ex.getMessage(), resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        try {
            // 1. Get body from Http request
            String json = getBody(req);
            // 2. parse body to obtain user class
            OrderCredential order = jsonToClass(json, OrderCredential.class);
            // 3.
            if (pathInfo != null && pathInfo.equals("/update")) {
                HttpSession session = req.getSession();
                String sessionLibrarianId = session.getAttribute("sessionUserId").toString();
                orderService.update(Integer.parseInt(sessionLibrarianId), order);
            }
//            else {
//                // get book id from path
//                int bookId = defineUrlPathParameter(req);
//                orderService.update(order, bookId, "NOT AVAILABLE");
//            }
            // 4. Create successful HTTP response with JSON body
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error putting orders " + ex.getMessage());
            buildErrorResponse(400, ex.getMessage(), resp);
        }
    }

}
