package com.example.EduConnect.controller;

import com.example.EduConnect.entity.User;
import com.example.EduConnect.model.ApiResponse;
import com.example.EduConnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<User> createUser(@Valid @RequestBody User user){
        User createdUser = userService.createUser(user);
        return new ApiResponse<>("User created successfully", createdUser, true, HttpStatus.CREATED.value());
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ApiResponse<List<User>> getAllUser(){
        List<User> users = userService.getAllUser();
        return new ApiResponse<>("Users fetched successfully", users, true, HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ApiResponse<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new ApiResponse<>("User fetched successfully", user, true, HttpStatus.OK.value());
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ApiResponse<User> getCurrentUser(){
        User user = userService.getCurrentUser();
        return new ApiResponse<>("Current user fetched successfully", user, true, HttpStatus.OK.value());
    }
}