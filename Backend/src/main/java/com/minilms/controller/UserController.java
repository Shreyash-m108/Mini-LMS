package com.minilms.controller;

import com.minilms.dto.userDto.CreateUserRequest;
import com.minilms.dto.userDto.ViewUserDTO;
import com.minilms.entity.User;
import com.minilms.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request){
        return userService.saveUser(request);
    }

    @GetMapping
    public List<ViewUserDTO> getUsers(){
        return userService.getAllUsers();
    }

}
