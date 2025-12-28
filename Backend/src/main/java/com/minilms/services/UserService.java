package com.minilms.services;

import com.minilms.dto.userDto.CreateUserRequest;
import com.minilms.dto.userDto.LoginRequest;
import com.minilms.dto.userDto.ViewUserDTO;
import com.minilms.entity.User;
import com.minilms.exceptions.DuplicateEmail;
import com.minilms.exceptions.IncorrectEmailOrPassword;
import com.minilms.exceptions.ResourceNotFound;
import com.minilms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail (String email){
        return userRepository.findByEmail(email);
    }

    //add user
    public User saveUser(CreateUserRequest request){
        User user = new User();
        if(findByEmail(request.getEmail()).isPresent()){
            throw new DuplicateEmail("email is already present.");
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setApproved(request.getApproved());
        user.setCreatedAt(request.getCreatedAt());
        return userRepository.save(user);
    }

    public List<ViewUserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream().map(
                        user -> new ViewUserDTO(
                                user.getId(),
                                user.getName(),
                                user.getEmail(),
                                user.getRole(),
                                user.getApproved(),
                                user.getCreatedAt()
                        )).toList();
    }

    public ViewUserDTO validLogin(LoginRequest request){
        User user = findByEmail(request.getEmail())
                .orElseThrow(()->new IncorrectEmailOrPassword("incorrect email"));

        if(!request.getPassword().equals(user.getPassword())){
            throw new IncorrectEmailOrPassword("incorrect password.");
        }

        return new ViewUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getApproved(),
                user.getCreatedAt()
        );

    }
}
