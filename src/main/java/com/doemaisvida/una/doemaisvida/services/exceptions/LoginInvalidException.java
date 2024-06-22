package com.doemaisvida.una.doemaisvida.services.exceptions;

public class LoginInvalidException extends RuntimeException {

    public LoginInvalidException(String msg){
        super(msg);
    }
}
