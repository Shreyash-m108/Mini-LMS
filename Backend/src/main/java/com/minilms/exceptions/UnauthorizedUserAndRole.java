package com.minilms.exceptions;

public class UnauthorizedUserAndRole extends RuntimeException{
    public UnauthorizedUserAndRole(String message){
        super(message);
    }
}
