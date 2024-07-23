package com.doemaisvida.una.doemaisvida.services.impl;

import com.doemaisvida.una.doemaisvida.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails{

	private Long id;

	private String name;

	private String email;

	private String password;


	public UserDetailsImpl(Long id, String name, String password, String email,
						   Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User usuario) {

		return new UserDetailsImpl(
				usuario.getId(),
				usuario.getName(),
				usuario.getPassword(),
				usuario.getEmail(),
				new ArrayList<>());
	}

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
