package com.doemaisvida.una.doemaisvida.services.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException (String msg){
        super(msg);
    }
}
