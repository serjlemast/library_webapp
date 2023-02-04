package com.example.liberary.service;

import com.example.liberary.model.Order;
import com.example.liberary.model.OrderCredential;
import com.example.liberary.model.Status;
import com.example.liberary.model.User;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    void create(int id, String booksId);

    List<OrderCredential> findAll();
    List<OrderCredential> findAll(Status status, int userId);

    void update(int id,OrderCredential order);
    void update(OrderCredential order,int id,String status);
    void delete(int id);

}
