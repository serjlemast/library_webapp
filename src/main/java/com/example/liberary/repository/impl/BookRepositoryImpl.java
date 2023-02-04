package com.example.liberary.repository.impl;

import com.example.liberary.model.Book;
import com.example.liberary.repository.AbstractRepository;
import com.example.liberary.repository.BookRepository;
import com.example.liberary.utils.HikariCPDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookRepositoryImpl extends AbstractRepository implements BookRepository {

    @Override
    public void create(Book book) {
        HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_NEW_BOOK_SQL_QUERY)) {
                preparedStatement.setInt(1, book.getId());
                preparedStatement.setString(2, book.getName());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setDate(4, book.getPubleshedDate());
                preparedStatement.setString(5, book.getInfo());
                preparedStatement.executeUpdate();
                return null;
            }
        }, "error inserting new book to database");
    }

    @Override
    public void update(Book book) {
        HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER_TEMPLATE_SQL_QUERY)) {
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setDate(3, book.getPubleshedDate());
                preparedStatement.setString(4, book.getInfo());
                preparedStatement.setInt(5, book.getId());
                preparedStatement.executeUpdate();
                return null;
            }
        }, "error updating book to database");
    }

    @Override
    public void delete(int id) {
        HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_BOOK_BY_ID_SQL_QUERY)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return null;
            }
        }, "error deleting book by id from database");
    }

    @Override
    public List<Book> findAll() {
        return HikariCPDataSource.execute((conn) -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(SELECT_BOOKS_SQL_QUERY)) {
                List<Book> bookList = new ArrayList<>();
                while (resultSet.next()) {
                    Book book = Book.builder()
                            .id(resultSet.getInt(BOOK_ID_COLUMN)) //
                            .name(resultSet.getString(BOOK_NAME_COLUMN)) //
                            .author(resultSet.getString(BOOK_AUTHOR_COLUMN)) //
                            .publeshedDate(resultSet.getDate(BOOK_PUBLISHED_DATE_COLUMN)) //
                            .info(resultSet.getString(BOOK_INFO_COLUMN)) //
                            .status(resultSet.getString(BOOK_STATUS_COLUMN)) //
                            .build();
                    bookList.add(book);
                }
                return bookList;
            }
        }, "error getting books from database");
    }

    @Override
    public Book find(int id) {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = findBookPrepareStatement(conn, id);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                Book.BookBuilder builder = Book.builder();
                if (resultSet.next()) {
                    builder
                            .id(resultSet.getInt(BOOK_ID_COLUMN)) //
                            .name(resultSet.getString(BOOK_NAME_COLUMN)) //
                            .author(resultSet.getString(BOOK_AUTHOR_COLUMN)) //
                            .publeshedDate(resultSet.getDate(BOOK_PUBLISHED_DATE_COLUMN)) //
                            .info(resultSet.getString(BOOK_INFO_COLUMN));
                }
                return builder.build();
            }
        }, "error getting book by id " + id + " from database ");
    }

    PreparedStatement findBookPrepareStatement(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID_SQL_QUERY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    public List<Book> find(String name, String author, String orderBy) {
        return HikariCPDataSource.execute((conn) -> {
            //todo
            try (PreparedStatement preparedStatement = findBookByCredentialPrepareStatement(conn, name, author, orderBy);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Book> bookList = new ArrayList<>();
                while (resultSet.next()) {
                    Book book = Book.builder()
                            .id(resultSet.getInt(BOOK_ID_COLUMN)) //
                            .name(resultSet.getString(BOOK_NAME_COLUMN)) //
                            .author(resultSet.getString(BOOK_AUTHOR_COLUMN)) //
                            .publeshedDate(resultSet.getDate(BOOK_PUBLISHED_DATE_COLUMN)) //
                            .info(resultSet.getString(BOOK_INFO_COLUMN)) //
                            .status(resultSet.getString(BOOK_STATUS_COLUMN)) //
                            .build();
                    bookList.add(book);
                }
                return bookList;
            }
        }, "error getting books by name " + name + " and author " + author + " from database");
    }

    PreparedStatement findBookByCredentialPrepareStatement(Connection connection, String name, String author, String orderBy) throws SQLException {
        String query = SELECT_BOOK_BY_CREDENTIAL_SQL_QUERY;
        if (!orderBy.isEmpty()) {
            query += " order by " + orderBy;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //todo
        if (Objects.isNull(name)) {
            name = "";
        }
        if (Objects.isNull(author)) {
            author = "";
        }
        String format = "%%%s%%";
        preparedStatement.setString(1, String.format(format, name));
        preparedStatement.setString(2, String.format(format, author));
        return preparedStatement;
    }


    @Override
    public void update(int id, String status) {
        HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_BOOK_BY_ENABLE_TEMPLATE_SQL_QUERY)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
                return null;
            }
        }, "error updating status by id " + id + " from database ");
    }
}
