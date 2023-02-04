package com.example.liberary.service;

import com.example.liberary.model.Book;

import java.util.List;

public interface BookService {

    void create(Book book);

    void update(Book book);

    void delete(int id);
    List<Book> findAll();

    Book find(int id);
    List<Book> find(String name ,String author,String orderBy);

    void update(int id,String status);
}
