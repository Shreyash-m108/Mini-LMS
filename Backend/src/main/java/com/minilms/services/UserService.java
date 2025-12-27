package com.minilms.services;

import com.minilms.dto.userDto.CreateUserRequest;
import com.minilms.dto.userDto.ViewUserDTO;
import com.minilms.entity.User;
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
    public User saveUser(CreateUserRequest createUserRequest){
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setRole(createUserRequest.getRole());
        user.setApproved(createUserRequest.getApproved());
        user.setCreatedAt(createUserRequest.getCreatedAt());
        return userRepository.save(user);
    }

    public List<ViewUserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream().map(
                        user -> new ViewUserDTO(
                                user.getName(),
                                user.getEmail(),
                                user.getRole(),
                                user.getApproved(),
                                user.getCreatedAt()
                        )).toList();
    }
}
