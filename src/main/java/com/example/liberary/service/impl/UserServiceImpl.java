package com.example.liberary.service.impl;

import com.example.liberary.exeption.ServiceException;
import com.example.liberary.model.User;
import com.example.liberary.model.UserCredential;
import com.example.liberary.repository.UserRepository;
import com.example.liberary.repository.impl.UserRepositoryImpl;
import com.example.liberary.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public User createUser(User user) {
        //todo mb validation
        userRepository.create(user);
        return user;
    }

    @Override
    public List<UserCredential> findAll(int offset) {
        //todo ROLE
        return userRepository.findAll(offset);
    }

    @Override
    public void updateStatus(int id, boolean status) {
        int statusCode = userRepository.updateStatus(id, status);
        if (statusCode > 0) {
            return;
        }
        throw new ServiceException("Cant update user status by id " + id);
    }

    @Override
    public void updateUser(User user) {
        int statusCode = userRepository.updateUser(user);
        if (statusCode > 0) {
            return;
        }
        throw new ServiceException("Cant update user (user id " + user.getId() + ")");
    }

    @Override
    public UserCredential find(int id) {
        return userRepository.find(id);
    }

    @Override
    public UserCredential find(String username, String password) {
        //todo
        UserCredential userCredential = userRepository.find(username, password);
        if (userCredential.getUsername() == null || userCredential.getRole() == null) {
            return null;
        }
        return userCredential;
    }

    @Override
    public void delete(int id) {
        int statusCode = userRepository.delete(id);
        if (statusCode > 0) {
            return;
        }
        throw new ServiceException("Cant delete user");
    }

    @Override
    public int getCountOfUser() {
        int statusCode = userRepository.getCountOfUsers();
        if (statusCode > 0) {
            return statusCode;
        }
        throw new ServiceException("Not found count of user");
    }
}
