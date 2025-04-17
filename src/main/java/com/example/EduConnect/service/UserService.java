package com.example.EduConnect.service;

import com.example.EduConnect.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUser();
    User getUserById(Long id);
}
