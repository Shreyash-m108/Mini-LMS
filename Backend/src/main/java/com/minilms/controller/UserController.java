package com.minilms.controller;

import com.minilms.entity.Role;
import com.minilms.entity.User;
import com.minilms.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        user.setRole(Role.STUDENT);
        user.setApproved(true);
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

}
