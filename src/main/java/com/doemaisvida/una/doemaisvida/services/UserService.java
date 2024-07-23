package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.User;

public interface UserService {

	public User createUser(User obj);

	public User update(Long id, User obj);


}
