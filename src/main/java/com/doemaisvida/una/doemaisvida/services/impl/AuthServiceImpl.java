package com.doemaisvida.una.doemaisvida.services.impl;

import com.doemaisvida.una.doemaisvida.DTO.AcessDTO;
import com.doemaisvida.una.doemaisvida.DTO.AuthenticationDTO;
import com.doemaisvida.una.doemaisvida.repositorys.UserRepository;
import com.doemaisvida.una.doemaisvida.security.jwt.JwtUtils;
import com.doemaisvida.una.doemaisvida.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticatioManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserRepository userRepository;

	public AcessDTO login(AuthenticationDTO authDto) {

		try {

			UsernamePasswordAuthenticationToken userAuth =
					new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword());

			Authentication authentication = authenticatioManager.authenticate(userAuth);

			UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();

			String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

			AcessDTO accessDto = new AcessDTO(token);

			return accessDto;

		}catch(BadCredentialsException e) {
			//TODO LOGIN OU SENHA INVALIDO
		} catch (IllegalArgumentException e){
			e.getMessage();
		}
		return new AcessDTO("Acesso negado");
	}
}
