package com.doemaisvida.una.doemaisvida.services.impl;

import com.doemaisvida.una.doemaisvida.DTO.UserCreateDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserDTO;
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

    private final EmailServiceImpl emailService;

    private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailServiceImpl emailService, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.userMapper = userMapper;
	}

	@Transactional
    public UserDTO createUser(UserCreateDTO obj) throws UserAlreadyExistsException {

        var user = userMapper.toUser(obj);
        System.out.println(obj.toString());
        System.out.println(user.toString());
        if (obj == null || user == null) throw new IllegalArgumentException("O objeto User fornecido é nulo");

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

            var userDTO = userMapper.toUserDTO(user);
			return userDTO;

        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar o usuário no banco de dados");
        }
    }

    public User update(Long id, User obj) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            updateData(user, obj);
            return userRepository.save(user);
        } else {
            throw new ObjectNotFoundException("Usuário não encontrado");
        }
    }

    private void updateData(User dataUp, User obj) {
        dataUp.setName(obj.getName());
        if (obj.getEmail() != null && !userRepository.existsByEmail(obj.getEmail())) {
            dataUp.setEmail(obj.getEmail());
        }
        dataUp.setLocation(obj.getLocation());
        dataUp.setImgUrl(obj.getImgUrl());
        if (obj.getCellPhone()!= null && !userRepository.existsByCellPhone(obj.getCellPhone())) {
            dataUp.setCellPhone(obj.getCellPhone());
        }

        if (obj.getPassword() != null && !obj.getPassword().isEmpty() &&
                obj.getPasswordConfirm() != null && !obj.getPasswordConfirm().isEmpty()) {
            if (Objects.equals(obj.getPassword(), obj.getPasswordConfirm())) {
                String encryptedPassword = passwordEncoder.encode(obj.getPassword());
                dataUp.setPassword(encryptedPassword);
                dataUp.setPasswordConfirm(encryptedPassword);
            } else {
                throw new InvalidPasswordException("Senhas não correspondem");
            }
        }
    }
}