package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.User;

public interface TokenService {

	User getUserFromToken(String token);
}
