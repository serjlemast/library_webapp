package com.example.liberary.repository;

import com.example.liberary.model.Role;
import com.example.liberary.model.User;
import com.example.liberary.model.UserCredential;

import java.util.List;

public interface UserRepository {
    void create(User user);
    int delete(int id);

    /**
     * block,unblock entity in the users_table
     */
    int updateStatus(int id,boolean status);
    int updateUser(User user);
    User find(int id);
    UserCredential find(String username, String password);
    List<User> findAll(int offset);

    int getCountOfUsers();

//    void getUser
}
