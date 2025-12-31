package com.minilms.dto.userDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
}
