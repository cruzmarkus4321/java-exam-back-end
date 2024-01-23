package com.java.exam.controller;

import com.java.exam.model.AuthResponse;
import com.java.exam.model.User;
import com.java.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /*@MutationMapping
    public User register(@Argument String username, @Argument String password, @Argument int userType) {
        return userService.register(username, password, userType);
    }*/

    @MutationMapping
    public AuthResponse login(@Argument String username, @Argument String password) {
        return userService.login(username, password);
    }
}
