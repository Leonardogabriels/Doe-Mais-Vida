package com.doemaisvida.una.doemaisvida.services.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (String msg){
        super(msg);
    }
}
