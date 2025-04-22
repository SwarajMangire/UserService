package com.example.demo.impl;

import com.example.demo.entities.Rating;
import com.example.demo.entities.User;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.reporsitory.UserRepositories;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= LoggerFactory.getLogger(UserService.class);

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
        User user = userRepositories.findById(userId).orElseThrow(() ->new ResourceNotFound("User with given not found !!" + userId));
        String url = "http://localhost:8082/rating/users/" + userId;
       ArrayList<Rating> info= restTemplate.getForObject(url, ArrayList.class);
       user.setRating(info);
       logger.info("{}",info);
        return user;
    }
}
