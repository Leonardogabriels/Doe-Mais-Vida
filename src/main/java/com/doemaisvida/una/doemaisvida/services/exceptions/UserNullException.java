package com.doemaisvida.una.doemaisvida.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

public class UserNullException extends RuntimeException {

	public UserNullException(String e) {
		super(e);
	}
}
