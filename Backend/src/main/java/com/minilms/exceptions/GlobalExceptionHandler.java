package com.minilms.exceptions;

import com.minilms.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateEmail.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateEmail(
            DuplicateEmail ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO(409, ex.getMessage()));
    }

    @ExceptionHandler(IncorrectEmailOrPassword.class)
    public ResponseEntity<ErrorResponseDTO> incorrectEmailOrPassword(IncorrectEmailOrPassword ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDTO(401, ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFound(ResourceNotFound ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(404,ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedUserAndRole.class)
    public ResponseEntity<ErrorResponseDTO> unauthorizedUserAndRole(ResourceNotFound ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDTO(401,ex.getMessage()));
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(500,"Something went wrong...try again after some time."));
    }

}
