package com.minilms.dto.userDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minilms.entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
