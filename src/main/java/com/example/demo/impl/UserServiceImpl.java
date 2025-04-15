package com.example.demo.impl;

import com.example.demo.entities.User;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.reporsitory.UserRepositories;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepositories.save((user));
    }

    @Override
    public List<User> getAllUser() {
        List<User> user = userRepositories.findAll();
        return user;
    }

    @Override
    public User getUser(String userId) {
        return userRepositories.findById(userId).orElseThrow(() ->new ResourceNotFound("User with given not found !!" + userId));
    }
}
