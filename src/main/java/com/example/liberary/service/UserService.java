package com.example.liberary.service;

import com.example.liberary.model.User;
import com.example.liberary.model.UserCredential;

import java.util.List;

public interface UserService {


    User createUser(User user);

    List<UserCredential> findAll(int offset);

    void updateStatus(int id,boolean status);
    void updateUser(User user);
    UserCredential find(int id);
    UserCredential find(String username, String password);

    void delete(int id);
    int getCountOfUser();
}
