package com.example.liberary.controller.admin;

import com.example.liberary.controller.base.AbstractHttpController;
import com.example.liberary.exeption.ApplicationException;
import com.example.liberary.model.Order;
import com.example.liberary.model.OrderCredential;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@WebServlet(urlPatterns = {"/admin/orders", "/admin/orders/*"})
public class OrderRestController extends AbstractHttpController {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    /**
     * Create new order
     * Http request:
     * - type: json
     * - body: Order.class + session
     * Http response statuses:
     * 200 - successful
     * 400 - error with 'errorMessage' key
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        try {
            // 1. Get body from Http request
            String json = getBody(req);
            // 2. parse body to obtain user class
            HttpSession session = req.getSession();
            String sessionUserId = session.getAttribute("sessionUserId").toString();
            OrderCredential order = jsonToClass(json, OrderCredential.class);
            // 3. create order
            orderService.create(Integer.parseInt(sessionUserId), order.getBookIds());
            // 4. Create successful HTTP response with JSON body
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error posting orders " + ex.getMessage());
            // if we have application error than we sent error status code with message
            buildErrorResponse(400, ex.getMessage(), resp);
        }
    }

    /**
     * Get all orders from data base
     * Http response statuses:
     * 200 - successful
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<OrderCredential> users = orderService.findAll();
            String json = toJson(users);
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error getting orders " + ex.getMessage());
            buildErrorResponse(404, ex.getMessage(), resp);
        }
    }

    /**
     * Update order by librarian
     * Http response statuses:
     * 200 - successful
     * 400 - error with 'errorMessage' key
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1. Get body from Http request
            String json = getBody(req);
            // 2. parse body to obtain user class
            OrderCredential order = jsonToClass(json, OrderCredential.class);
            // 3. update user status
            orderService.update(order.getId(),order);
            // 4. Create successful HTTP response with JSON body
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error putting orders " + ex.getMessage());
            buildErrorResponse(400, ex.getMessage(), resp);
        }
    }

    //todo doDelete
}
