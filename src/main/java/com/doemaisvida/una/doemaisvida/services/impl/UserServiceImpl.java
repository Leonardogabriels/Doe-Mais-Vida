package com.doemaisvida.una.doemaisvida.services.impl;

import com.doemaisvida.una.doemaisvida.DTO.UserCreateDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserUpdateDTO;
import com.doemaisvida.una.doemaisvida.entities.User;
import com.doemaisvida.una.doemaisvida.repositorys.UserRepository;
import com.doemaisvida.una.doemaisvida.services.UserService;
import com.doemaisvida.una.doemaisvida.services.exceptions.*;
import com.doemaisvida.una.doemaisvida.services.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}

	@Transactional
    public UserDTO createUser(UserCreateDTO obj) throws UserAlreadyExistsException {

        var user = userMapper.toUser(obj);
        if (user == null) {
            throw new UserNullException("O objeto User fornecido é nulo");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }

        if (userRepository.existsByCellPhone(user.getCellPhone())) {
            throw new UserAlreadyExistsException("Celular já cadastrado");
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            throw new InvalidPasswordException("As senhas não correspondem");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(passwordEncoder.encode(user.getPasswordConfirm()));

        try {
           User objs=  userRepository.save(user);

           //Implementar uma nova forma de e-mail, essa demora muito na requisição
          /*  emailService.sendEmail(obj.getEmail(),
                    "Novo usuário cadastrado",
                    "Seu cadastro em nosso site foi realizado com sucesso ");*/

			return userMapper.toUserDTO(user);

        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar o usuário no banco de dados");
        }
    }

    @Transactional
    public UserDTO update(User user, UserUpdateDTO obj) {
            updateData(user, obj);
            return userMapper.toUserDTO(user);
    }

    private void updateData(User dataUp,UserUpdateDTO obj) {
        if (obj.getEmail() != null && !userRepository.existsByEmail(obj.getEmail())) {
            dataUp.setEmail(obj.getEmail());
        }else {
            throw new EmailException("Já existe uma conta com o email: " + obj.getEmail() + " cadastrado, tente um diferenre");
        }
        dataUp.setLocation(obj.getLocation());
        dataUp.setImgUrl(obj.getImgUrl());
        if (obj.getCellPhone()!= null && !userRepository.existsByCellPhone(obj.getCellPhone())) {
            dataUp.setCellPhone(obj.getCellPhone());
        }

    }
}
