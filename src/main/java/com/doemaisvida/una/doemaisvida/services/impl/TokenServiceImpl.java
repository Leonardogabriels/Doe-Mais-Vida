package com.doemaisvida.una.doemaisvida.services.impl;

import com.doemaisvida.una.doemaisvida.entities.User;
import com.doemaisvida.una.doemaisvida.repositorys.UserRepository;
import com.doemaisvida.una.doemaisvida.security.jwt.JwtUtils;
import com.doemaisvida.una.doemaisvida.services.TokenService;
import com.doemaisvida.una.doemaisvida.services.exceptions.EntityNotFoundException;
import com.doemaisvida.una.doemaisvida.services.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final JwtUtils jwtTokenUtil;
	private final UserRepository userRepository;

	public  User getUserFromToken(String token) {
		if (token == null || !token.startsWith("Bearer ")) {
			throw new UnauthorizedException("Token inválido ou ausente");
		}

		String jwtToken = token.replace("Bearer ", "");
		Long userId = jwtTokenUtil.getUserIdFromToken(jwtToken);

		return userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
	}
}

