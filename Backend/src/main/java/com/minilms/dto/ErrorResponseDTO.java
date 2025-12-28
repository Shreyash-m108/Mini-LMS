package com.minilms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDTO {

        private int staus;
        private String message;
        private LocalDateTime time;

        public ErrorResponseDTO(int staus, String message){
            this.staus =staus;
            this.message = message;
            this.time = LocalDateTime.now();
        }
}
