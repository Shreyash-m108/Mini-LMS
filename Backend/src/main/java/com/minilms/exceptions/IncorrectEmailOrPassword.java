package com.minilms.exceptions;

public class IncorrectEmailOrPassword extends RuntimeException {
    public IncorrectEmailOrPassword(String message){
        super(message);
    }
}
