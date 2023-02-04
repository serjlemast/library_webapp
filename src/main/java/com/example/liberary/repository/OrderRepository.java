package com.example.liberary.repository;

import com.example.liberary.model.*;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository {

    void create(int id, String booksId);

    void update(int id, OrderCredential order);

    List<OrderCredential> findAll();

    List<OrderCredential> findAll(Status status, int userId);

    void delete(int id);
}
