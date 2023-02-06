package com.example.liberary.controller.reader;

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

@WebServlet(urlPatterns = {"/client/orders", "/client/orders/*"})
public class ReaderOrderController extends AbstractHttpController {

    private static final Logger logger = LoggerFactory.getLogger(ReaderOrderController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1. Get body from Http request
            String json = getBody(req);
            // 2. parse body to obtain order class
            OrderCredential order = jsonToClass(json, OrderCredential.class);
            // 3. create order by user
            HttpSession session = req.getSession();
            String sessionUserId = session.getAttribute("sessionUserId").toString();
            // 3. create order
            orderService.create(Integer.parseInt(sessionUserId), order.getBookIds());
            //
            String jsonResponse = toJson(order);
            // 4. Create successful HTTP response with JSON body
            writeJsonResponseBody(200, jsonResponse, resp);
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
        String pathInfo = req.getPathInfo();
        try {
            HttpSession session = req.getSession();
            String sessionUserId = session.getAttribute("sessionUserId").toString();
            Status status = Status.CREATED;
            if (pathInfo != null && pathInfo.contains("/find")) {
                String orderStatus = req.getParameter("status");
                if (Objects.nonNull(orderStatus)) {
                    status = Status.getStatus(orderStatus);
                }
            }
            List<OrderCredential> orders = orderService.findAll(status, Integer.parseInt(sessionUserId));
            String json = toJson(orders);
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error getting orders " + ex.getMessage());
            buildErrorResponse(404, ex.getMessage(), resp);
        }
    }

}
