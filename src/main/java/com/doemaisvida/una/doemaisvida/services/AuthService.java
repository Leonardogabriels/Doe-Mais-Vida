package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.DTO.AcessDTO;
import com.doemaisvida.una.doemaisvida.DTO.AuthenticationDTO;

public interface AuthService {

	public AcessDTO login(AuthenticationDTO authDto);
}
