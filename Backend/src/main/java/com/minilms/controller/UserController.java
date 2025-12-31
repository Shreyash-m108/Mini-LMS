package com.minilms.controller;

import com.minilms.dto.dashboardDto.DashboardDTO;
import com.minilms.dto.userDto.CreateUserRequest;
import com.minilms.dto.userDto.LoginRequest;
import com.minilms.dto.userDto.RegisterDTO;
import com.minilms.dto.userDto.ViewUserDTO;
import com.minilms.entity.User;
import com.minilms.services.DashboardService;
import com.minilms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final DashboardService dashboardService;

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request){
        return userService.saveUser(request);
    }

    @GetMapping
    public List<ViewUserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ViewUserDTO register(@RequestBody RegisterDTO request){
         return userService.register(request);

    }

    @PostMapping("/login")
    public ViewUserDTO login (@RequestBody LoginRequest request){
        return userService.validLogin(request);
    }

    @PostMapping("/dashboard")
    public DashboardDTO getDashboard(){
        return dashboardService.mockDashboard();
    }

}
