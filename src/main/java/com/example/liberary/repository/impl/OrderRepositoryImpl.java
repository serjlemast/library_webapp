package com.example.liberary.repository.impl;

import com.example.liberary.model.Order;
import com.example.liberary.model.OrderCredential;
import com.example.liberary.model.Status;
import com.example.liberary.repository.AbstractRepository;
import com.example.liberary.repository.OrderRepository;
import com.example.liberary.utils.HikariCPDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl extends AbstractRepository implements OrderRepository {


    @Override
    public void create(int id, String booksId) {
        HikariCPDataSource.execute((conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call CREATE_NEW_ORDER(?,?)}")) {
                cs.setInt(1, id);
                cs.setString(2, booksId);
                cs.execute();
                return null;
            }
        }, "error inserting new order to database");
    }

    @Override
    public void update(int id, OrderCredential order) {
        HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_ORDER_BY_LIBRARIAN)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, order.getStatus().name());
                preparedStatement.setBoolean(3, order.isSubscription());
                preparedStatement.setDate(4, order.getReturnDate());
                preparedStatement.setInt(5, order.getId());
                preparedStatement.executeUpdate();
                return null;
            }
        }, "error updating order to database");
    }


    @Override
    public List<OrderCredential> findAll() {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ORDERS_SQL_QUERY); ResultSet resultSet = preparedStatement.executeQuery()) {
                List<OrderCredential> orderList = new ArrayList<>();
                while (resultSet.next()) {
                    OrderCredential order = OrderCredential.builder()
                            .id(resultSet.getInt(ORDER_ID_COLUMN))
                            .userId(resultSet.getInt(ORDER_USER_ID_COLUMN))
                            .librarianId(resultSet.getInt(ORDER_LIBRARIAN_ID_COLUMN))
                            .bookIds(resultSet.getString("book_ids"))
                            .createDate(resultSet.getDate("create_date"))
                            .returnDate(resultSet.getDate("return_date"))
                            .subscription(resultSet.getBoolean("subscription"))
                            .build();
                    //todo
                    order.setStatus(Status.valueOf(resultSet.getString("status")));
                    orderList.add(order);
                }
                return orderList;
            }
        }, "error getting orders from database");
    }


    @Override
    public List<OrderCredential> findAll(Status status, int userId) {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = findUserByStatusPrepareStatement(conn, status, userId);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                List<OrderCredential> orderList = new ArrayList<>();
                while (resultSet.next()) {
                    OrderCredential order = OrderCredential.builder()
                            .id(resultSet.getInt("o.id"))
                            .bookIds(resultSet.getString("book_ids"))
                            .userId(resultSet.getInt("user_id"))
                            .bookNames(resultSet.getString("book_names"))
                            .createDate(resultSet.getDate("o.create_date"))
                            .returnDate(resultSet.getDate("return_date"))
                            .subscription(resultSet.getBoolean("subscription"))
                            .status(Status.getStatus(resultSet.getString("status")))
                            .build();
                    orderList.add(order);
                }
                return orderList;
            }
        }, "error getting orders by status from database");
    }

    PreparedStatement findUserByStatusPrepareStatement(Connection connection, Status status, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_CREDENTIALS_SQL_QUERY);
        String format = "%%%s%%";
        preparedStatement.setString(1, String.format(format, status.name()));
        preparedStatement.setInt(2, id);
        return preparedStatement;
    }


    @Override
    public void delete(int id) {
        HikariCPDataSource.execute((conn) -> {
            try (CallableStatement cs = conn.prepareCall("{call DELETE_FULL_ORDER(?)}")) {
                cs.setInt(1, id);
                cs.execute();
                return null;
            }
        }, "error error deleting order by id " + id + " from database ");
    }
}
