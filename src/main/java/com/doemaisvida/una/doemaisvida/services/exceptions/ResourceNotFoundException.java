package com.doemaisvida.una.doemaisvida.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object email){
        super("Recurso Não encontrado " + email);
    }
}
