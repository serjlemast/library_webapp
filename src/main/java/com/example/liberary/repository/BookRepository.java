package com.example.liberary.repository;

import com.example.liberary.model.Book;

import java.util.List;
import java.util.Map;

public interface BookRepository {
    void create(Book book);

    /**
     * update all record
     */
    void update(Book book);

    void delete(int id);

    //todo mb with parameters
    List<Book> findAll();

    Book find(int id);

    List<Book> find(String name, String author,String orderBy);

    /**
     * update user status
     *
     * @param id     - user.id
     * @param status - status true/false
     */
    void update(int id, String status);
}
