package com.example.liberary.controller.base;


import com.example.liberary.service.BookService;
import com.example.liberary.service.OrderService;
import com.example.liberary.service.UserService;
import com.example.liberary.service.impl.BookServiceImpl;
import com.example.liberary.service.impl.OrderServiceImpl;
import com.example.liberary.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;

import com.example.liberary.exeption.ApplicationException;

import static com.example.liberary.constant.WebConstant.DATE_FORMAT_JSON;

@Log
public class AbstractHttpController extends HttpServlet {

    private final Gson gson;
    protected final UserService userService;
    protected final BookService bookService;
    protected final OrderService orderService;

    public AbstractHttpController() {
        //todo
        gson = new GsonBuilder().setDateFormat(DATE_FORMAT_JSON).create();
        userService = new UserServiceImpl();
        bookService = new BookServiceImpl();
        orderService = new OrderServiceImpl();
    }

    protected String toJson(Object ob) {
        String json = gson.toJson(ob);
        log.info("object to json: " + json);
        return json;
    }

    protected <T> T jsonToClass(String json, Class<T> clazz) {
        try {
            log.info("parse json to class, json " + json + ", class: " + clazz.getName());
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new ApplicationException("Json parsing error, json - " + json, e);
        }
    }


    protected String getBody(HttpServletRequest req) {
        try (BufferedReader reader = req.getReader()) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new ApplicationException("Error parsing request body", e);
        }
    }


    protected String buildJson(String key, Object value) {
        String jsonValue = (value instanceof Number)
                ? String.valueOf(value)
                : value.toString();
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty(key, jsonValue);
        return gson.toJson(jsonObj);
    }

    protected String buildJson(String key, int value, String nextKey, int nextValue) {
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty(key, value);
        jsonObj.addProperty(nextKey, nextValue);
        return gson.toJson(jsonObj);
    }

    protected void writeJsonResponseBody(int status, String json, HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(status);
            out.print(json);
            out.flush();
        } catch (IOException ex) {
            throw new ApplicationException("Error creating JSON HTTP response", ex);
        }
    }

    protected void buildErrorResponse(int status, String message, HttpServletResponse resp) {
        String errorJson = buildJson("errorMessage", message);
        writeJsonResponseBody(status, errorJson, resp);
    }

    protected int defineUrlPathParameter(HttpServletRequest req) {
        String credentialId = req.getPathInfo();
        credentialId = credentialId.substring(1);
        String result = credentialId.replaceAll("[^0-9.]", "");
        if (result.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(result);
    }

    protected String getRoleName(String roleName) {
        roleName = roleName.replaceAll("[\"{}:,]", "");
        roleName = roleName.replaceAll("^.{4}", "");
        return roleName;
    }
}
