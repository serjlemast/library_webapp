package com.example.liberary.service.impl;

import com.example.liberary.model.Order;
import com.example.liberary.model.OrderCredential;
import com.example.liberary.model.Status;
import com.example.liberary.repository.BookRepository;
import com.example.liberary.repository.OrderRepository;
import com.example.liberary.repository.impl.BookRepositoryImpl;
import com.example.liberary.repository.impl.OrderRepositoryImpl;
import com.example.liberary.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl() {
        orderRepository = new OrderRepositoryImpl();
        bookRepository = new BookRepositoryImpl();
    }

    @Override
    public void create(int id, String bookIds) {
        orderRepository.create(id, bookIds);
    }

    @Override
    public List<OrderCredential> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderCredential> findAll(Status status, int userId) {
        return orderRepository.findAll(status, userId);
    }

    @Override
    public void update(int id,OrderCredential order) {
        orderRepository.update(id,order);
    }

    @Override
    public void update(OrderCredential order, int bookId, String bookStatus) {
        orderRepository.update(order.getId(), order);
        bookRepository.update(bookId, bookStatus);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }
}
