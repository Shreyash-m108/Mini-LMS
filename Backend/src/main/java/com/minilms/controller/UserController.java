package com.minilms.controller;

import com.minilms.dto.userDto.CreateUserRequest;
import com.minilms.dto.userDto.LoginRequest;
import com.minilms.dto.userDto.ViewUserDTO;
import com.minilms.entity.User;
import com.minilms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request){
        return userService.saveUser(request);
    }

    @GetMapping
    public List<ViewUserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User register(@RequestBody CreateUserRequest request){
        return createUser(request);
    }

    @PostMapping("/login")
    public ViewUserDTO login (@RequestBody LoginRequest request){
        return userService.validLogin(request);
    }

}
