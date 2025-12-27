package com.minilms.dto.userDto;

import com.minilms.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private Boolean approved;
    private LocalDateTime createdAt = LocalDateTime.now();
}
