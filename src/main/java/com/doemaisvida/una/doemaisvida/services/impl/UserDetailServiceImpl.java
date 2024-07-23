package com.doemaisvida.una.doemaisvida.services.impl;

import com.doemaisvida.una.doemaisvida.entities.User;
import com.doemaisvida.una.doemaisvida.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User usuario = usuarioRepository.findByEmail(email).get();
		return UserDetailsImpl.build(usuario);
	}

}
