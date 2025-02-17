package com.doemaisvida.una.doemaisvida.Controller.exception;

import java.time.Instant;
import java.util.Map;

public class ValidationError extends StandardError {
	private Map<String, Object> errors;

	public ValidationError() {
	}

	public ValidationError(Instant timestamp, Integer status, String error, String menssage, String path, Map<String, Object> errors) {
		super(timestamp, status, error, menssage, path);
		this.errors = errors;
	}

	public Map<String, Object> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}
}
