package com.example.liberary.repository.impl;

import com.example.liberary.model.Role;
import com.example.liberary.model.User;
import com.example.liberary.model.UserCredential;
import com.example.liberary.repository.AbstractRepository;
import com.example.liberary.repository.UserRepository;
import com.example.liberary.utils.HikariCPDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    @Override
    public void create(User user) {
        HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement q = conn.prepareStatement(INSERT_NEW_USER_SQL_QUERY)) {
                q.setString(1, user.getUsername());
                q.setString(2, user.getPassword());
                q.setString(3, user.getFirstName());
                q.setString(4, user.getSecondName());
                q.executeUpdate();
                return null;
            }
        }, "error inserting new user to database");
    }

    @Override
    public int delete(int id) {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement q = conn.prepareStatement(DELETE_USER_BY_ID_SQL_QUERY);) {
                q.setInt(1, id);
                return q.executeUpdate();
            }
        }, "error deleting user by id " + id + " from database");
    }

    @Override
    public int updateStatus(int id, boolean status) {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement q = conn.prepareStatement(UPDATE_USER_BY_BLOCK_TEMPLATE_SQL_QUERY)) {
                q.setBoolean(1, status);
                q.setInt(2, id);
                return q.executeUpdate();
            }
        }, "error updating user status by id " + id + " from database");
    }

    @Override
    public int updateUser(User user) {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement q = conn.prepareStatement(UPDATE_USER_TEMPLATE_SQL_QUERY)) {
                q.setString(1, user.getUsername());
                q.setString(2, user.getPassword());
                q.setString(3, user.getFirstName());
                q.setString(4, user.getSecondName());
                q.setInt(5, user.getId());
                return q.executeUpdate();
            }
        }, "error updating user");
    }

    @Override
    public User find(int id) {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = findUserByIdPrepareStatement(conn, id);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                User.UserBuilder builder = User.builder();
                if (resultSet.next()) {
                    builder
                            .id(resultSet.getInt(USER_ID_COLUMN))
                            .firstName(resultSet.getString(USER_FIRST_NAME_COLUMN))
                            .secondName(resultSet.getString(USER_SECOND_NAME_COLUMN))
                            .password(resultSet.getString(USER_PASSWORD_COLUMN))
                            .blocked(resultSet.getBoolean(USER_BLOCKED_COLUMN))
                            .username(resultSet.getString(USER_NAME_COLUMN));
                }
                return builder.build();
            }
        }, "error getting users from database by id " + id);
    }

    PreparedStatement findUserByIdPrepareStatement(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL_QUERY);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    @Override
    public UserCredential find(String username, String password) {
        return HikariCPDataSource.execute((conn) -> {
            try (PreparedStatement preparedStatement = findUserByCredentialPrepareStatement(conn, username, password);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                UserCredential.UserCredentialBuilder builder = UserCredential.builder();
                if (resultSet.next()) {
                    builder
                            .id(resultSet.getInt(USER_ID_COLUMN))
                            .fullName(resultSet.getString("full_name"))
                            .username(resultSet.getString(USER_NAME_COLUMN))
                            .role(Role.valueOf(resultSet.getString("roles_table.name")));
                }
                return builder.build();
            }
        }, "error getting users from database by username " + username);
    }

    PreparedStatement findUserByCredentialPrepareStatement(Connection connection, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_CREDENTIAL_SQL_QUERY);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }

    @Override
    public List<User> findAll(int offset) {
        return HikariCPDataSource.execute((conn) -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(SELECT_USERS_SQL_QUERY + calculateOffset(offset))) {
                List<User> userList = new ArrayList<>();
                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getInt(USER_ID_COLUMN))
                            .firstName(resultSet.getString(USER_FIRST_NAME_COLUMN))
                            .secondName(resultSet.getString(USER_SECOND_NAME_COLUMN))
                            .password(resultSet.getString(USER_PASSWORD_COLUMN))
                            .blocked(resultSet.getBoolean(USER_BLOCKED_COLUMN))
                            .username(resultSet.getString(USER_NAME_COLUMN))
                            .build();
                    userList.add(user);
                }
                return userList;
            }
        }, "error getting users from database");
    }

    private int calculateOffset(int offset) {
        if (offset < 1) {
            return 1;
        }
        return (offset - 1) * DEFAULT_COUNT_OF_USERS_ON_PAGE;
    }

    @Override
    public int getCountOfUsers() {
        return HikariCPDataSource.execute((conn -> {
            try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(SELECT_COUNT_OF_USERS_SQL_QUERY)) {
                if (resultSet.next()) {
                    return resultSet.getInt("count_id");
                } else {
                    return 0;
                }
            }
        }), "error getting count of users");
    }
}
