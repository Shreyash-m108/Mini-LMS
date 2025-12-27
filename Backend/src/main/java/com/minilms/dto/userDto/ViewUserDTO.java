package com.minilms.dto.userDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minilms.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ViewUserDTO {
    private String name;
    private String email;
    private Role role;
    private boolean approved;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private LocalDateTime createdAt;
}
