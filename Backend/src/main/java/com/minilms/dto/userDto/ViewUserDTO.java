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
    private Long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Role role;
    private boolean approved;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private LocalDateTime createdAt;

    public ViewUserDTO(Long id, String firstName, String lastName, String email) {
    }
}
