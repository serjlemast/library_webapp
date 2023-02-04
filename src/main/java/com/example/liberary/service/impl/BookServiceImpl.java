package com.example.liberary.service.impl;

import com.example.liberary.model.Book;
import com.example.liberary.repository.BookRepository;
import com.example.liberary.repository.impl.BookRepositoryImpl;
import com.example.liberary.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl() {
        bookRepository = new BookRepositoryImpl();
    }

    @Override
    public void create(Book book) {
        bookRepository.create(book);
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book find(int id) {
        return bookRepository.find(id);
    }

    @Override
    public List<Book> find(String name, String author, String orderBy) {
        return bookRepository.find(name, author, orderBy);
    }

    @Override
    public void update(int id, String status) {
        bookRepository.update(id, status);
    }
}
