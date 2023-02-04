package com.example.liberary.controller.admin;

import com.example.liberary.controller.base.AbstractHttpController;
import com.example.liberary.exeption.ApplicationException;
import com.example.liberary.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.*;

import java.util.List;

@WebServlet(urlPatterns = {"/admin/users", "/admin/users/*"})
public class UserRestController extends AbstractHttpController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    /**
     * Create new usr
     * Http request:
     * - type: json
     * - body: User.class
     * Http response statuses:
     * 200 - successful
     * 400 - error with 'errorMessage' key
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1. Get body from Http request
            String json = getBody(req);
            // 2. parse body to obtain user class
            User user = jsonToClass(json, User.class);
            // 3. create user
            User userResponse = userService.createUser(user);
            //
            String jsonResponse = toJson(userResponse);
            // 4. Create successful HTTP response with JSON body
            writeJsonResponseBody(200, jsonResponse, resp);
        } catch (ApplicationException e) {
            // if we have application error than we sent error status code with message
            buildErrorResponse(400, e.getMessage(), resp);
        }
    }

    /**
     * Get all users from data base
     * Http response statuses:
     * 200 - successful
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/count")) {
            try {
                int total = userService.getCountOfUser();
                String json = buildJson("total", total, "limit", 2);
                writeJsonResponseBody(200, json, resp);
            } catch (ApplicationException ex) {
                logger.warn("Error getting count of user" + ex.getMessage());
                buildErrorResponse(404, ex.getMessage(), resp);
            }
        }
        try {
            String pageNumber = req.getParameter("page_number");
            int offset = defineOffset(pageNumber);
            List<User> users = userService.findAll(offset);

            if (pathInfo != null && pathInfo.equals("/find")) {
                String id = req.getParameter("id");
                String password = req.getParameter("password");
                String username = req.getParameter("username");
                if (id != null) {
                    users.clear();
                    users.add(userService.find(Integer.parseInt(id)));
                }
            }

            String json = toJson(users);

            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error getting user " + ex.getMessage());
            buildErrorResponse(404, ex.getMessage(), resp);
        }
    }

    int defineOffset(String pageNumber) {
        if (pageNumber == null || pageNumber.equals("1") || pageNumber.equals("0")) {
            return 1;
        }
        try {
            return Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    /**
     * Update status by id
     * Http response statuses:
     * 200 - successful
     * 400 - error with 'errorMessage' key
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
        try {
            // 1. Get body from Http request
            String json = getBody(req);
            // 2. parse body to obtain user class
            User user = jsonToClass(json, User.class);
            // 3. update user status
            //todo
            if (pathInfo != null && pathInfo.equals("/blocked")) {
                userService.updateStatus(user.getId(), user.isBlocked());
            } else {
                userService.updateUser(user);
            }
            // 4. Create successful HTTP response with JSON body
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error putting user " + ex.getMessage());
            buildErrorResponse(400, ex.getMessage(), resp);
        }
    }

    /**
     * Delete user by id
     * Http response statuses:
     * 200 - successful
     * 400 - error with 'errorMessage' key
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1. Get user id from path
            int userId = defineUrlPathParameter(req);
            // 2. Delete user from data base by user id
            userService.delete(userId);
            // 3.Create json object with user id
            String json = buildJson("id", userId);
            // 4. Create successful HTTP response with JSON body
            writeJsonResponseBody(200, json, resp);
        } catch (ApplicationException ex) {
            logger.warn("Error deleting user " + ex.getMessage());
            // if we have application error than we sent error status code with message
            buildErrorResponse(400, ex.getMessage(), resp);
        }
    }

}
