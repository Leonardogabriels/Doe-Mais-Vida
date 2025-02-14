package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.DTO.UserCreateDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserDTO;
import com.doemaisvida.una.doemaisvida.entities.User;

public interface UserService {

	public UserDTO createUser(UserCreateDTO obj);

	public User update(Long id, User obj);


}
